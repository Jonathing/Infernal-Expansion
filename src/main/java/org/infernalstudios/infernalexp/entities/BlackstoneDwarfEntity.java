/*
 * Copyright 2022 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.entities;

import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.ZombifiedPiglin;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ToolActions;
import org.infernalstudios.infernalexp.config.InfernalExpansionConfig;
import org.infernalstudios.infernalexp.init.IESoundEvents;

import javax.annotation.Nullable;
import java.util.UUID;

//import net.minecraft.entity.projectile.ArrowEntity;

public class BlackstoneDwarfEntity extends PathfinderMob implements RangedAttackMob, NeutralMob {

    private static final EntityDataAccessor<Boolean> PLAYER_CREATED = SynchedEntityData.defineId(BlackstoneDwarfEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ATTACK_TIMER = SynchedEntityData.defineId(BlackstoneDwarfEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ROCK_TIMER = SynchedEntityData.defineId(BlackstoneDwarfEntity.class, EntityDataSerializers.INT);
    private static final UniformInt RANGED_INT = TimeUtil.rangeOfSeconds(20, 39);
    private int angerTime;
    private UUID angerTarget;

    public BlackstoneDwarfEntity(EntityType<? extends PathfinderMob> type, Level worldIn) {
        super(type, worldIn);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PLAYER_CREATED, false);
        this.entityData.define(ATTACK_TIMER, 0);
        this.entityData.define(ROCK_TIMER, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("PlayerCreated", this.isPlayerCreated());
        this.addPersistentAngerSaveData(tag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setPlayerCreated(tag.getBoolean("PlayerCreated"));
        this.readPersistentAngerSaveData(this.level, tag);
    }

    // ATTRIBUTES
    public static AttributeSupplier.Builder setCustomAttributes() {
        return Mob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, 40.0D)
            .add(Attributes.ATTACK_DAMAGE, 10.0D)
            .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 2.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.40D);
        // .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D);
    }

    public void aiStep() {
        super.aiStep();
        if (this.getAttackTimer() > 0) {
            this.entityData.set(ATTACK_TIMER, this.getAttackTimer() - 1);
        }
        if (this.getRockTimer() > 0) {
            this.entityData.set(ROCK_TIMER, this.getRockTimer() - 1);
        }
    }

    public int getAttackTimer() {
        return this.entityData.get(ATTACK_TIMER);
    }

    public int getRockTimer() {
        return this.entityData.get(ROCK_TIMER);
    }

    public boolean isPlayerCreated() {
        return this.entityData.get(PLAYER_CREATED);
    }

    public void setPlayerCreated(boolean isCreated) {
        this.entityData.set(PLAYER_CREATED, isCreated);
    }

    public boolean doHurtTarget(Entity entityIn) {
        this.entityData.set(ATTACK_TIMER, 10);
        this.playSound(IESoundEvents.BASALT_GIANT_DEATH.get(), 1.0F, 1.0F);
        boolean disableShield = false;
        float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float f1 = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        float f2 = (float) this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);

        if (entityIn instanceof Player player && player.getUseItem().canPerformAction(ToolActions.SHIELD_BLOCK)) {
            attackFling(entityIn, f2 * 3, 2.0);
            entityIn.hurtMarked = true;
            disableShield = true;
        }

        boolean flag = entityIn.hurt(DamageSource.mobAttack(this), f1);

        if (flag) {
            attackFling(entityIn, f2, 0.6);
        }
        if (disableShield) {
            ((Player) entityIn).disableShield(true);
        }

        this.playSound(IESoundEvents.BASALT_GIANT_HURT.get(), 1.0F, 1.0F);
        return flag;
    }

    private void attackFling(Entity entityIn, float f2, double height) {
        ((LivingEntity) entityIn).knockback(f2, Mth.sin(this.getYRot() * ((float) Math.PI / 180F)), -Mth.cos(this.getYRot() * ((float) Math.PI / 180F)));
        entityIn.setDeltaMovement(entityIn.getDeltaMovement().add(0.0D, height, 0.0D));
        this.doEnchantDamageEffects(this, entityIn);
    }

    /*
     * @Override public boolean attackEntityFrom(DamageSource source, float amount)
     * { if(source.getImmediateSource() instanceof ArrowEntity){ return false; }
     * return super.attackEntityFrom(source, amount); }
     */

    // ---

    // BEHAVIOUR
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new DwarfMeleeAttackGoal(this, 0.6D, 5.0F, true));
        this.goalSelector.addGoal(0, new RockThrowAttackGoal(this, 0.6D, 60, 100, 5.0F, 16.0F));
        this.goalSelector.addGoal(1, new WaterAvoidingRandomStrollGoal(this, 0.5d));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        if (InfernalExpansionConfig.MobInteractions.DWARF_ATTACK_PIGLIN.getBoolean()) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractPiglin.class, true, false));
        }
        if (InfernalExpansionConfig.MobInteractions.DWARF_ATTACK_ZOMBIE_PIGLIN.getBoolean()) {
            this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, ZombifiedPiglin.class, true, false));
        }
        if (InfernalExpansionConfig.MobInteractions.DWARF_ATTACK_PLAYER.getBoolean()) {
            this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        }
        if (InfernalExpansionConfig.MobInteractions.GLOWSQUITO_ATTACK_DWARF.getBoolean()) {
            this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, GlowsquitoEntity.class, true));
        }
    }

    @Override
    public boolean canAttackType(EntityType<?> targetType) {
        if (this.isPlayerCreated() && targetType == EntityType.PLAYER) {
            return false;
        } else {
            return super.canAttackType(targetType);
        }
    }

    // EXP POINTS
    @Override
    protected int getExperienceReward(Player player) {
        return 2 + this.level.random.nextInt(2);
    }

    public boolean fireImmune() {
        return true;
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.angerTime = time;
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.angerTarget;
    }

    @Override
    public void setPersistentAngerTarget(@Nullable UUID target) {
        this.angerTarget = target;
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(RANGED_INT.sample(this.random));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float damage) {
        this.entityData.set(ROCK_TIMER, 10);
        for(int i = 0; i < 5; ++i) {
            switch (i) {
                case 0:
                    throwRock(this.level, this, 1.6F, 1.0F, 0, 0.1F);
                    break;
                case 1:
                    throwRock(this.level, this, 1.6F, 1.0F, -5, 0.1F);
                    break;
                case 2:
                    throwRock(this.level, this, 1.6F, 1.0F, -10, 0.1F);
                    break;
                case 3:
                    throwRock(this.level, this, 1.6F, 1.0F, 5, 0.1F);
                    break;
                default:
                case 4:
                    throwRock(this.level, this, 1.6F, 1.0F, 10, 0.1F);
                    break;
            }
        }
    }

    private void throwRock(Level level, LivingEntity shooter, float shotPower, float componentMultiplier, float horizontalVariance, float verticalVariance) {
        if (!level.isClientSide) {
            RockEntity rock = new RockEntity(this.level, this);
            Vec3 vec31 = shooter.getUpVector(1.0F);
            Quaternion quaternion = new Quaternion(new Vector3f(vec31), horizontalVariance, true);
            Vec3 vec3 = shooter.getViewVector(1.0F);
            Vector3f launchVector = new Vector3f(vec3);
            launchVector.transform(quaternion);
            rock.shoot(launchVector.x(), launchVector.y() + shooter.getRandom().nextFloat(2 * verticalVariance) - verticalVariance, launchVector.z(), shotPower, componentMultiplier);

            level.addFreshEntity(rock);
            level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), SoundEvents.EGG_THROW, SoundSource.PLAYERS, 1.0F, shooter.getRandom().nextFloat(0.5F) + 0.5F);
        }
    }

    static class RockThrowAttackGoal extends RangedAttackGoal {
        private final BlackstoneDwarfEntity dwarf;
        private final float minRangeSqr;

        public RockThrowAttackGoal(BlackstoneDwarfEntity dwarf, double speedModifier, int minInterval, int maxInterval, float minRange, float maxRange) {
            super(dwarf, speedModifier, minInterval, maxInterval, maxRange);
            this.dwarf = dwarf;
            this.minRangeSqr = minRange * minRange;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.dwarf.getTarget() != null && this.dwarf.distanceToSqr(this.dwarf.getTarget()) >= minRangeSqr;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.dwarf.getTarget() != null && this.dwarf.distanceToSqr(this.dwarf.getTarget()) >= minRangeSqr;
        }
    }

    static class DwarfMeleeAttackGoal extends MeleeAttackGoal {
        private final BlackstoneDwarfEntity dwarf;
        private final float maxRangeSqr;
        public DwarfMeleeAttackGoal(BlackstoneDwarfEntity dwarf, double speedModifier, float maxRange, boolean followIfNotSeen) {
            super(dwarf, speedModifier, followIfNotSeen);
            this.dwarf = dwarf;
            this.maxRangeSqr = maxRange * maxRange;
        }

        @Override
        public boolean canUse() {
            return super.canUse() && this.dwarf.getTarget() != null && this.dwarf.distanceToSqr(this.dwarf.getTarget()) < this.maxRangeSqr;
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && this.dwarf.getTarget() != null && this.dwarf.distanceToSqr(this.dwarf.getTarget()) < this.maxRangeSqr;
        }
    }
}
