/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderHandEvent
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$RenderTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.trolmastercard.sexmod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class GoblinPlayer
extends GirlPlayerHomeEntity
implements GirlMaster {
    public static final float aI = 2.0f;
    public static final DataParameter<String> ax = EntityDataManager.createKey(GoblinPlayer.class, (DataSerializer)DataSerializers.STRING).getSerializer().createKey(122);
    public static final DataParameter<Boolean> aA = EntityDataManager.createKey(GoblinPlayer.class, (DataSerializer)DataSerializers.BOOLEAN).getSerializer().createKey(126);
    int aJ = 0;
    int az = -1;
    int aG = 0;
    GirlAnimationState aw = GirlAnimationState.NULL;
    int aE = -1;
    boolean aC = false;
    boolean aB = true;
    boolean ay = true;
    boolean aF = false;
    boolean aH = false;
    String aD = "";

    public GoblinPlayer(World world) {
        super(world);
    }

    public GoblinPlayer(World world, UUID uUID) {
        super(world, uUID);
    }

    @Override
    public float i() {
        return 0.9f;
    }

    @Override
    public ModelPartProvider a(int i) {
        return new ModelCube8();
    }

    @Override
    public String c(int i) {
        return "textures/entity/kobold/hand.png";
    }

    @Override
    public Vec3i b(int i) {
        String[] stringArray = GoblinPlayer.getAttributeStrings(this);
        try {
            if (stringArray.length < 8) {
                return super.checkHomeData(i);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return GirlColor.values()[Integer.parseInt(stringArray[7])].a();
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        TribeColor tribeColor = TribeColor.values()[this.getRNG().nextInt(TribeColor.values().length)];
        this.DataManager.register(au, (Object)new BlockPos(tribeColor.getColor()));
        this.DataManager.register(as, (Object)GoblinNpc.DefaultTribeColor.name());
        this.DataManager.register(aA, (Object)false);
        this.DataManager.register(ax, (Object)"");
    }

    @Override
    public void b(String string, UUID uUID) {
        try {
            if ("anal".equals(string)) {
                this.checkHomeData(uUID);
                this.checkHomeData(GirlAnimationState.NELSON_INTRO);
                this.a(this.getOutfitIndex(), GirlAnimationState.NELSON_INTRO);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if ("paizuri".equals(string)) {
                this.checkHomeData(uUID);
                this.checkHomeData(GirlAnimationState.PAIZURI_START);
                this.a(this.getOutfitIndex(), GirlAnimationState.PAIZURI_START);
                this.f(0);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public boolean b(EntityPlayer entityPlayer) {
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiGirlCommandMenu(this, entityPlayer, new String[]{"anal", "paizuri"}, null, false));
        return true;
    }

    @Override
    public EntityPlayer c(EntityPlayer entityPlayer) {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return entityPlayer;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer2 = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer2 == null) {
                return entityPlayer;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return entityPlayer2;
    }

    @Override
    public boolean d() {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (this.e() != null && Minecraft.getMinecraft().player.getPersistentID().equals(this.getBoundPlayerUuid())) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean z() {
        boolean flag;
        UUID uUID = this.e();
        try {
            flag = uUID == null;
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    public Vec3d c(Vec3d vec3d, float f) {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        Vec3d vec3d2 = entityPlayer.getPositionVector();
        Vec3d vec3d3 = new Vec3d(entityPlayer.lastTickPosX, entityPlayer.lastTickPosY, entityPlayer.lastTickPosZ);
        return LerpMath.lerpVec3d(vec3d3, vec3d2, (double)f);
    }

    void c(EntityPlayer entityPlayer) {
        try {
            if (this.getCurrentAction() != GirlAnimationState.NULL) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if (this.e() != null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if (GoblinNpc.playerHasSingleGoblin(entityPlayer.getPersistentID())) {
                entityPlayer.sendStatusMessage((ITextComponent)new TextComponentString("you are already carrying a Goblin"), true);
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.a(entityPlayer.getPersistentID());
        this.checkHomeData(GirlAnimationState.PICK_UP);
        this.checkHomeData(45);
        EntityPlayer entityPlayer2 = this.getBoundPlayer();
        try {
            if (entityPlayer2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            entityPlayer2.setNoGravity(true);
            entityPlayer2.noClip = true;
            if (this.world.isRemote) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(false), (EntityPlayerMP)entityPlayer2);
    }

    @Override
    protected String a(StringBuilder stringBuilder) {
        GirlEffectEntity.appendRandomNumber(stringBuilder, 3);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 2);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 7);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 7);
        GirlEffectEntity.appendRandomNumber(stringBuilder, 5);
        GirlEffectEntity.appendRandomNumber(stringBuilder, MarkColor.values().length - 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, GirlColor.values().length - 1);
        GirlEffectEntity.appendRandomNumber(stringBuilder, TribeColor.values().length - 1);
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 0);
        return stringBuilder.toString();
    }

    @Override
    public ArrayList<Integer> D() {
        return new ArrayList<Integer>(){
            {
                this.add(4);
                this.add(3);
                this.add(3);
                this.add(16);
                this.add(16);
                this.add(6);
                this.add(MarkColor.values().length);
                this.add(GirlColor.values().length);
                this.add(TribeColor.values().length);
            }
        };
    }

    @Override
    public List<Integer> u() {
        return Collections.singletonList(2);
    }

    @Override
    protected void a() {
        GoblinPlayerRenderer.clearColorCache();
        GoblinNpcRenderer.clearColorCache();
    }

    public float getEyeHeight() {
        return 0.75f;
    }

    @Override
    public boolean o() {
        boolean flag;
        block5: {
            block4: {
                try {
                    try {
                        if (!this.Q() && this.e() == null) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.rethrow(runtimeException);
                    }
                    flag = true;
                    break block5;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
            }
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean a(GirlAnimationState girlAnimationState, EntityPlayer entityPlayer) {
        float f;
        float f2;
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer2 = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer2 == null) {
                return false;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        float f3 = entityPlayer.rotationYaw;
        try {
            f2 = girlAnimationState == GirlAnimationState.PICK_UP ? 180.0f : 0.0f;
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        float f4 = f2;
        float f5 = entityPlayer2.rotationYaw - 90.0f + f4;
        float f6 = entityPlayer2.rotationYaw + 90.0f + f4;
        try {
            if (f3 < f5) {
                entityPlayer.rotationYaw = f5;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if (f3 > f6) {
                entityPlayer.rotationYaw = f6;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        float f7 = entityPlayer.rotationPitch;
        try {
            f = girlAnimationState == GirlAnimationState.PICK_UP ? 0.0f : 37.5f;
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        float f8 = f;
        try {
            if (f7 > f8) {
                entityPlayer.rotationPitch = f8;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return true;
    }

    @Override
    public Vec3d b(Vec3d vec3d, float f) {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return vec3d;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        float f2 = LerpMath.lerp(entityPlayer.prevRenderYawOffset, entityPlayer.renderYawOffset, f);
        Vec3d vec3d2 = vec3d;
        float f3 = 135.0f;
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        if (girlAnimationState == GirlAnimationState.PICK_UP) {
            vec3d2 = new Vec3d(vec3d.x, vec3d.y, -vec3d.z);
            f3 += 40.0f;
        } else if (girlAnimationState != GirlAnimationState.START_THROWING) {
            vec3d2 = vec3d2.subtract(0.0, 2.0, 0.0);
        }
        vec3d2 = VectorMath.rotateYaw(vec3d2, f2 + f3);
        return vec3d2;
    }

    @SideOnly(value=Side.CLIENT)
    void f() {
        block7: {
            EntityPlayer entityPlayer = this.getBoundPlayer();
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
            try {
                try {
                    if (this.getCurrentAction() != GirlAnimationState.START_THROWING) break block7;
                    entityPlayer.isDead = false;
                    if (this.world.loadedEntityList.contains(entityPlayer)) break block7;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
                this.world.spawnEntity((Entity)entityPlayer);
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
        }
    }

    @Override
    public void onUpdate() {
        try {
            GoblinNpc.syncThrowState(this);
            this.d();
            this.getRenderPosition();
            super.onUpdate();
            if (!this.world.isRemote) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.isBoundToLocalPlayer();
        GirlAnimationState girlAnimationState = this.getCurrentAction();
        this.d(girlAnimationState);
        this.c(girlAnimationState);
        this.aw = girlAnimationState;
    }

    @Override
    public boolean E() {
        boolean flag;
        try {
            flag = this.e() != null;
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return flag;
    }

    void j() {
        block16: {
            GirlAnimationState girlAnimationState = this.getCurrentAction();
            try {
                if (girlAnimationState == GirlAnimationState.THROWN) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.START_THROWING || this.resetHomeData() <= 15) break block16;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
                return;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
        }
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(uUID);
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer2 = this.getBoundPlayer();
        try {
            if (entityPlayer2 == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        entityPlayer2.noClip = true;
        entityPlayer2.setNoGravity(true);
        entityPlayer2.setPosition(entityPlayer.posX, entityPlayer.posY + 2.0, entityPlayer.posZ);
    }

    void d() {
        GoblinPlayer goblin = this;
        int i = goblin.resetHomeData();
        try {
            if (i == -1) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        goblin.c(++i);
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        if (i == 15) {
            float f;
            float f2;
            block17: {
                Vec3d vec3d = GoblinNpc.getBoundPlayerPos(this);
                f2 = GoblinNpc.getBoundPlayerPitch(this);
                f = GoblinNpc.getBoundPlayerYaw(this);
                try {
                    try {
                        if (!this.world.isRemote || !this.isBoundToLocalPlayer()) break block17;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.rethrow(runtimeException);
                    }
                    AnimationInputLock.setAnimationLocked(true);
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
            }
            Vec3d vec3d = GoblinNpc.rotateVec3d(new Vec3d(0.0, 0.0, 1.5), f2, f);
            try {
                entityPlayer.motionX = vec3d.x;
                entityPlayer.motionY = vec3d.y;
                entityPlayer.motionZ = vec3d.z;
                if (!this.world.isRemote) {
                    this.checkHomeData(f);
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
        }
        try {
            entityPlayer.noClip = false;
            entityPlayer.setNoGravity(false);
            if (i == 39) {
                this.c(-1);
                this.checkHomeData(GirlAnimationState.THROWN);
                this.hasGirl((UUID)null);
                this.a((UUID)null);
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void updateAITasks() {
        super.updateAITasks();
        GoblinNpc.handlePickUp(this);
        this.getTargetPos();
        this.e();
    }

    void e() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.STAND_UP) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if (++this.aJ < 37) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.aJ = 0;
        this.checkHomeData(GirlAnimationState.NULL);
    }

    void o() {
        try {
            if (this.getCurrentAction() != GirlAnimationState.THROWN) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if (!entityPlayer.onGround) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        int i = this.d() + 1;
        try {
            this.a(i);
            if (i < 30) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.a(0);
        this.checkHomeData(GirlAnimationState.STAND_UP);
    }

    @Override
    @Nullable
    public UUID e() {
        String string = (String)this.DataManager.get(ax);
        try {
            if ("".equals(string)) {
                return null;
            }
        }
        catch (Exception exception) {
            throw GoblinPlayer.rethrow(exception);
        }
        try {
            return UUID.fromString((String)this.DataManager.get(ax));
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public void a(UUID uUID) {
        try {
            if (uUID == null) {
                this.DataManager.set(ax, (Object)"");
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.DataManager.set(ax, (Object)uUID.toString());
    }

    public EntityPlayer r() {
        UUID uUID = this.e();
        try {
            if (uUID == null) {
                return null;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return this.world.getPlayerEntityByUUID(uUID);
    }

    @Override
    public void c(int i) {
        this.az = i;
    }

    @Override
    public int a() {
        return this.az;
    }

    @Override
    public void a(int i) {
        this.aG = i;
    }

    @Override
    public int d() {
        return this.aG;
    }

    @Override
    public void a(GirlAnimationState girlAnimationState) {
        this.aw = girlAnimationState;
    }

    @Override
    public GirlAnimationState b() {
        return this.aw;
    }

    @Override
    public void b(int i) {
        this.aE = i;
    }

    @Override
    public int c() {
        return this.aE;
    }

    @Override
    public void g() {
        try {
            super.g();
            this.DataManager.set(aA, (Object)false);
            if (this.e() == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.a((UUID)null);
        EntityPlayer entityPlayer = this.getBoundPlayer();
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        NetworkHandler.channel.sendTo((IMessage)new PacketSetPlayerMovement(true), (EntityPlayerMP)entityPlayer);
    }

    @SideOnly(value=Side.CLIENT)
    void c(GirlAnimationState girlAnimationState) {
        block4: {
            try {
                try {
                    if (girlAnimationState != GirlAnimationState.NELSON_FAST || this.aw == GirlAnimationState.NELSON_FAST) break block4;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
                this.aF = false;
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    void d(GirlAnimationState girlAnimationState) {
        Minecraft minecraft = Minecraft.getMinecraft();
        try {
            if (!minecraft.player.getPersistentID().equals(this.getSexPlayerUuid())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            if (minecraft.gameSettings.thirdPersonView != 0) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        try {
            switch (girlAnimationState) {
                case NELSON_CUM: 
                case NELSON_FAST: 
                case NELSON_INTRO: 
                case NELSON_SLOW: {
                    minecraft.gameSettings.thirdPersonView = 2;
                    break;
                }
                default: {
                    return;
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
    }

    @Override
    public void a(List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i : list) {
            GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, i);
        }
        GirlEffectEntity.appendZeroPaddedNumber(stringBuilder, 1);
        this.DataManager.set(at, (Object)stringBuilder.toString());
    }

    @Override
    @Nullable
    protected GirlAnimationState c(GirlAnimationState girlAnimationState) {
        switch (girlAnimationState) {
            case PAIZURI_IDLE: 
            case PAIZURI_SLOW: {
                return GirlAnimationState.PAIZURI_FAST;
            }
            case BREEDING_SLOW_0: {
                return GirlAnimationState.BREEDING_FAST_0;
            }
            case BREEDING_SLOW_2: {
                return GirlAnimationState.BREEDING_FAST_2;
            }
            case NELSON_SLOW: {
                return GirlAnimationState.NELSON_FAST;
            }
        }
        return null;
    }

    @Override
    public void b(GirlAnimationState girlAnimationState) {
        block41: {
            GirlAnimationState girlAnimationState2;
            block40: {
                block39: {
                    block37: {
                        block35: {
                            block33: {
                                girlAnimationState2 = this.getCurrentAction();
                                try {
                                    block34: {
                                        try {
                                            try {
                                                if (girlAnimationState2 != GirlAnimationState.PAIZURI_CUM) break block33;
                                                if (girlAnimationState == GirlAnimationState.PAIZURI_SLOW) break block34;
                                            }
                                            catch (RuntimeException runtimeException) {
                                                throw GoblinPlayer.rethrow(runtimeException);
                                            }
                                            if (girlAnimationState != GirlAnimationState.PAIZURI_FAST) break block33;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GoblinPlayer.rethrow(runtimeException);
                                        }
                                    }
                                    return;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GoblinPlayer.rethrow(runtimeException);
                                }
                            }
                            try {
                                block36: {
                                    try {
                                        try {
                                            if (girlAnimationState2 != GirlAnimationState.NELSON_CUM) break block35;
                                            if (girlAnimationState == GirlAnimationState.NELSON_SLOW) break block36;
                                        }
                                        catch (RuntimeException runtimeException) {
                                            throw GoblinPlayer.rethrow(runtimeException);
                                        }
                                        if (girlAnimationState != GirlAnimationState.NELSON_FAST) break block35;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GoblinPlayer.rethrow(runtimeException);
                                    }
                                }
                                return;
                            }
                            catch (RuntimeException runtimeException) {
                                throw GoblinPlayer.rethrow(runtimeException);
                            }
                        }
                        try {
                            block38: {
                                try {
                                    try {
                                        if (girlAnimationState2 != GirlAnimationState.BREEDING_CUM_0) break block37;
                                        if (girlAnimationState == GirlAnimationState.BREEDING_SLOW_0) break block38;
                                    }
                                    catch (RuntimeException runtimeException) {
                                        throw GoblinPlayer.rethrow(runtimeException);
                                    }
                                    if (girlAnimationState != GirlAnimationState.BREEDING_FAST_0) break block37;
                                }
                                catch (RuntimeException runtimeException) {
                                    throw GoblinPlayer.rethrow(runtimeException);
                                }
                            }
                            return;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GoblinPlayer.rethrow(runtimeException);
                        }
                    }
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.PAIZURI_START || this.world.isRemote) break block39;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GoblinPlayer.rethrow(runtimeException);
                        }
                        this.getBoundPlayerUuid();
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.rethrow(runtimeException);
                    }
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.NELSON_INTRO || this.world.isRemote) break block40;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.rethrow(runtimeException);
                    }
                    this.getWalkState();
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
            }
            try {
                if (girlAnimationState == GirlAnimationState.NELSON_CUM) {
                    this.DataManager.set(aA, (Object)true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
            try {
                try {
                    if (girlAnimationState2 != GirlAnimationState.NELSON_CUM || girlAnimationState == GirlAnimationState.NELSON_CUM) break block41;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.rethrow(runtimeException);
                }
                this.DataManager.set(aA, (Object)false);
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.rethrow(runtimeException);
            }
        }
        super.checkHomeData(girlAnimationState);
    }

    void q() {
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.checkHomeData(entityPlayer.rotationYaw);
        this.noClip = true;
        this.setNoGravity(true);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ - 1.0);
    }

    void m() {
        EntityPlayer entityPlayer = this.world.getPlayerEntityByUUID(this.getSexPlayerUuid());
        try {
            if (entityPlayer == null) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        this.checkHomeData(entityPlayer.rotationYaw + 180.0f);
        this.noClip = true;
        this.setNoGravity(true);
        entityPlayer.setNoGravity(true);
        entityPlayer.noClip = true;
        entityPlayer.setPositionAndUpdate(entityPlayer.posX, entityPlayer.posY - 0.5, entityPlayer.posZ - (double)0.6f);
        entityPlayer.rotationPitch = 70.0f;
        entityPlayer.prevRotationPitch = 70.0f;
    }

    @Override
    public boolean l() {
        boolean flag;
        try {
            flag = this.e() == null;
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        return flag;
    }

    @Override
    public void b(EntityPlayer entityPlayer) {
        try {
            if (!entityPlayer.getPersistentID().equals(this.e())) {
                return;
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        PacketResetGirl.Handler.openGui(this);
        this.a(false);
        this.checkHomeData(GirlAnimationState.NULL);
        this.a((UUID)null);
    }

    @Override
    protected GirlAnimationState a(GirlAnimationState girlAnimationState) {
        switch (girlAnimationState) {
            case PAIZURI_SLOW: 
            case PAIZURI_FAST: 
            case PAIZURI_FAST_CONTINUES: {
                return GirlAnimationState.PAIZURI_CUM;
            }
            case BREEDING_1: {
                return GirlAnimationState.BREEDING_CUM_1;
            }
            case BREEDING_SLOW_2: 
            case BREEDING_FAST_2: {
                return GirlAnimationState.BREEDING_CUM_2;
            }
            case NELSON_FAST: 
            case NELSON_SLOW: {
                return GirlAnimationState.NELSON_CUM;
            }
        }
        return null;
    }

    @Override
    protected <E extends IAnimatable> PlayState a(AnimationEvent<E> animEvent) {
        if (this.world instanceof PreviewWorld) {
            return PlayState.STOP;
        }
        block5 : switch (animEvent.getController().getName()) {
            case "eyes": {
                if (this.getCurrentAction() != GirlAnimationState.NULL || !this.getCurrentAction().autoBlink) {
                    this.a("animation.goblin.null", true, animEvent);
                    break;
                }
                this.a("animation.goblin.blink", true, animEvent);
                break;
            }
            case "movement": {
                if (this.getCurrentAction() != GirlAnimationState.NULL) {
                    this.a("animation.goblin.null", true, animEvent);
                    break;
                }
                if (this.ak) {
                    this.a("animation.goblin.sit", true, animEvent);
                    break;
                }
                if (this.MovementController.getCurrentAnimation() != null && this.MovementController.getCurrentAnimation().animationName.contains("fly") && this.af) {
                    boolean bl = this.aC = !this.aC;
                }
                if (!this.af) {
                    this.a("animation.goblin.fly" + (this.aC ? "2" : ""), true, animEvent);
                    break;
                }
                if (Math.abs(this.ao.x) + Math.abs(this.ao.y) > 0.0f) {
                    if (this.aj) {
                        this.MovementController.setAnimationSpeed(1.2f);
                        this.a("animation.goblin.running", true, animEvent);
                        break;
                    }
                    if (this.ao.y >= -0.1f) {
                        this.MovementController.setAnimationSpeed(2.0);
                        this.a("animation.goblin.walk", true, animEvent);
                        break;
                    }
                    this.MovementController.setAnimationSpeed(1.5);
                    this.a("animation.goblin.backwards_walk", true, animEvent);
                    break;
                }
                this.a("animation.goblin.idle", true, animEvent);
                break;
            }
            case "action": {
                Minecraft minecraft = Minecraft.getMinecraft();
                String string = minecraft.player.getPersistentID().equals(this.e()) && minecraft.gameSettings.thirdPersonView == 0 ? "1" : "3";
                switch (this.getCurrentAction()) {
                    case SHOULDER_IDLE: {
                        this.a("animation.goblin.shoulder_idle", true, animEvent);
                        break block5;
                    }
                    case PICK_UP: {
                        this.a(String.format("animation.goblin.pick_up_%sperson", string), true, animEvent);
                        break block5;
                    }
                    case START_THROWING: {
                        this.a(String.format("animation.goblin.throw_%sperson", string), true, animEvent);
                        break block5;
                    }
                    case THROWN: {
                        this.a("animation.goblin.thrown", true, animEvent);
                        break block5;
                    }
                    case NULL: {
                        this.a("animation.goblin.null", true, animEvent);
                        break block5;
                    }
                    case STAND_UP: {
                        this.a("animation.goblin.stand_up", false, animEvent);
                        break block5;
                    }
                    case STRIP: {
                        this.a("animation.goblin.strip", false, animEvent);
                        break block5;
                    }
                    case ATTACK: {
                        this.a("animation.goblin.attack" + this.S, false, animEvent);
                        break block5;
                    }
                    case BOW: {
                        this.a("animation.goblin.bowcharge", false, animEvent);
                        break block5;
                    }
                    case SIT: {
                        this.a("animation.goblin.sit", true, animEvent);
                        break block5;
                    }
                    case NELSON_INTRO: {
                        this.a("animation.goblin.nelson_intro", true, animEvent);
                        break block5;
                    }
                    case NELSON_SLOW: {
                        this.a("animation.goblin.nelson_slow" + (this.ay ? "" : "2"), true, animEvent);
                        break block5;
                    }
                    case NELSON_FAST: {
                        this.a("animation.goblin.nelson_fast" + (this.aF ? "c" : "s"), true, animEvent);
                        break block5;
                    }
                    case NELSON_CUM: {
                        this.a("animation.goblin.nelson_cum", true, animEvent);
                        break block5;
                    }
                    case BREEDING_INTRO_0: {
                        this.a("animation.goblin.breeding_intro_1", true, animEvent);
                        break block5;
                    }
                    case BREEDING_INTRO_1: {
                        this.a("animation.goblin.breeding_intro_2", true, animEvent);
                        break block5;
                    }
                    case BREEDING_INTRO_2: {
                        this.a("animation.goblin.breeding_intro_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_SLOW_0: {
                        this.a("animation.goblin.breeding_slow_1" + (this.aB ? "l" : "r"), true, animEvent);
                        break block5;
                    }
                    case BREEDING_SLOW_2: {
                        this.a("animation.goblin.breeding_slow_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_FAST_0: {
                        this.a("animation.goblin.breeding_fast_1" + (this.aH ? "c" : "s"), true, animEvent);
                        break block5;
                    }
                    case BREEDING_FAST_2: {
                        this.a("animation.goblin.breeding_fast_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_CUM_0: {
                        this.a("animation.goblin.breeding_cum_1", true, animEvent);
                        break block5;
                    }
                    case BREEDING_CUM_1: {
                        this.a("animation.goblin.breeding_cum_2", true, animEvent);
                        break block5;
                    }
                    case BREEDING_CUM_2: {
                        this.a("animation.goblin.breeding_cum_3", true, animEvent);
                        break block5;
                    }
                    case BREEDING_1: {
                        this.a("animation.goblin.breeding_2", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_START: {
                        this.a("animation.goblin.paizuri_start", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_SLOW: {
                        this.a("animation.goblin.paizuri_slow" + this.aD, true, animEvent);
                        break block5;
                    }
                    case PAIZURI_FAST: {
                        this.a("animation.goblin.paizuri_fast", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_FAST_CONTINUES: {
                        this.a("animation.goblin.paizuri_fast_countinues", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_IDLE: {
                        this.a("animation.goblin.paizuri_idle", true, animEvent);
                        break block5;
                    }
                    case PAIZURI_CUM: {
                        this.a("animation.goblin.paizuri_cum", true, animEvent);
                    }
                }
            }
        }
        return PlayState.CONTINUE;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerControllers(AnimationData animationData) {
        try {
            if (this.ActionController == null) {
                this.canStartInteraction();
            }
        }
        catch (RuntimeException runtimeException) {
            throw GoblinPlayer.rethrow(runtimeException);
        }
        AnimationController.ISoundListener iSoundListener = arg1 -> {
            switch (arg1.sound) {
                case "attackDone": {
                    if (++this.S != 3) break;
                    this.S = 0;
                    break;
                }
                case "catchEh": {
                    this.a("ehh..");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchAkward": {
                    this.a("awkward..");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchWell": {
                    this.a("well...");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchRather": {
                    this.a("would you rather have this stupid... thing?");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchMe": {
                    this.a("...or use me?~");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "catchDone": {
                    if (!"bj".equals(this.DataManager.get(GirlEntity.BlowjobStageKey))) break;
                    this.b(GirlAnimationState.CATCH_BJ);
                    break;
                }
                case "catchBjDone": {
                    this.b(GirlAnimationState.CATCH_BJ_IDLE);
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    GirlEntity.openActionMenuWithItems(entityPlayerSP, this, new String[]{"use her", "take ur stuff back"}, null, false);
                    break;
                }
                case "paizuriChoice": {
                    this.a("good choice!~");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "paizuriBoth": {
                    this.a("...for both of us!");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "paizruiUse": {
                    this.a("now use me like a fuck toy!~");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "paizuriSwitch": {
                    if (this.getRNG().nextBoolean()) break;
                    this.aD = "".equals(this.aD) ? "2" : "";
                    break;
                }
                case "touch": {
                    this.a(ModSounds.MISC_TOUCH, 3.0f);
                    break;
                }
                case "pound": {
                    this.a(ModSounds.MISC_POUNDING, new int[0]);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.04f);
                    break;
                }
                case "paizuri_startDone": {
                    this.b(GirlAnimationState.PAIZURI_IDLE);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "paizuriFastDone": {
                    this.b(GirlAnimationState.PAIZURI_SLOW);
                    break;
                }
                case "paizuriFastReady": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.PAIZURI_FAST_CONTINUES);
                    break;
                }
                case "paizuriFastContinuesReady":
                case "neslon_fastBackSwitch": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.N();
                    break;
                }
                case "smallPound": {
                    this.a(ModSounds.MISC_POUNDING, 0.25f);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.addProgress(0.02f);
                    break;
                }
                case "paizruiCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
                    entityPlayerSP.rotationPitch = 70.0f;
                    entityPlayerSP.prevRotationPitch = 70.0f;
                    break;
                }
                case "blackScreen": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiTransitionScreen.startTransition();
                    break;
                }
                case "cumSound": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 3.0f);
                    break;
                }
                case "jumpCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    Minecraft minecraft = Minecraft.getMinecraft();
                    minecraft.player.rotationYaw = this.I().floatValue() + 170.0f;
                    minecraft.player.rotationPitch = -20.0f;
                    minecraft.player.rotationYawHead = minecraft.player.rotationYaw;
                    minecraft.gameSettings.thirdPersonView = 2;
                    break;
                }
                case "breedingHmm": {
                    if (this.isOwnedByLocalPlayer()) {
                        Minecraft minecraft = Minecraft.getMinecraft();
                        minecraft.player.rotationYaw = this.I().floatValue() + 180.0f;
                        minecraft.player.rotationPitch = -15.0f;
                        minecraft.player.rotationYawHead = minecraft.player.rotationYaw;
                        minecraft.gameSettings.thirdPersonView = 0;
                    }
                    this.a("hmm...");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "breedingFound": {
                    this.a("guess we found a worthy breeding partner!");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "breedingEnough": {
                    this.a("Eh.. go pin him down, before he runs off!");
                    this.a(ModSounds.MISC_PLOB, new int[0]);
                    break;
                }
                case "breedingCam2": {
                    if (this.isOwnedByLocalPlayer()) {
                        Minecraft minecraft = Minecraft.getMinecraft();
                        minecraft.gameSettings.thirdPersonView = 2;
                        minecraft.player.rotationYaw = this.I().floatValue() - 120.0f;
                        minecraft.player.rotationPitch = -30.0f;
                    }
                }
                case "breedingIntroDone": {
                    this.b(GirlAnimationState.BREEDING_SLOW_0);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "breeding_slow1Done": {
                    if (this.getRNG().nextBoolean()) {
                        boolean bl = this.aB = !this.aB;
                    }
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.BREEDING_FAST_0);
                    this.aH = false;
                    break;
                }
                case "breeding_fast1Done": {
                    this.b(GirlAnimationState.BREEDING_SLOW_0);
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.aH = false;
                    break;
                }
                case "breeding_fast1Ready": {
                    if (!this.isOwnedByLocalPlayer() || !AnimationInputLock.SneakPressed) break;
                    this.aH = true;
                    this.N();
                    this.ActionController.tickOffset = 0.0;
                    break;
                }
                case "cum": {
                    this.a(ModSounds.MISC_SMALLINSERTS, 2.0f);
                    break;
                }
                case "breeding_intro_3Done": {
                    this.b(GirlAnimationState.BREEDING_SLOW_2);
                    break;
                }
                case "breeding_3_wiggle": {
                    if (!this.getRNG().nextBoolean()) break;
                    this.ActionController.tickOffset = 0.0;
                    break;
                }
                case "breeding_fast_3Done": {
                    if (!this.isOwnedByLocalPlayer() || AnimationInputLock.SneakPressed) break;
                    this.b(GirlAnimationState.BREEDING_SLOW_2);
                    break;
                }
                case "breeding_intro_2Done": {
                    this.b(GirlAnimationState.BREEDING_1);
                    break;
                }
                case "breeding_cumCam": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    Minecraft minecraft = Minecraft.getMinecraft();
                    minecraft.gameSettings.thirdPersonView = 0;
                    minecraft.player.rotationYaw = this.I().floatValue() + 180.0f;
                    minecraft.player.rotationPitch = -15.0f;
                    minecraft.player.rotationYawHead = minecraft.player.rotationYaw;
                    minecraft.gameSettings.thirdPersonView = 0;
                    break;
                }
                case "neslon_introDone": {
                    this.b(GirlAnimationState.NELSON_SLOW);
                    if (!this.isOwnedByLocalPlayer()) break;
                    GuiHud.showHud();
                    break;
                }
                case "nelson_slowDone": {
                    if (!this.getRNG().nextBoolean()) break;
                    this.ay = !this.ay;
                    break;
                }
                case "neslon_fastSwitch": {
                    if (!this.isOwnedByLocalPlayer()) {
                        this.aF = true;
                        return;
                    }
                    if (!AnimationInputLock.SneakPressed) break;
                    this.aF = true;
                    break;
                }
                case "nelsonFastDone": {
                    this.aF = false;
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.b(GirlAnimationState.NELSON_SLOW);
                    break;
                }
                case "paizuriCumDone":
                case "nelson_cumDone": {
                    if (!this.isOwnedByLocalPlayer()) break;
                    this.resetAimTarget();
                    this.b(GirlAnimationState.NULL);
                }
            }
        };
        this.ActionController.registerSoundListener(iSoundListener);
        this.MovementController.transitionLengthTicks = 2.0;
        animationData.addAnimationController(this.ActionController);
        animationData.addAnimationController(this.MovementController);
        animationData.addAnimationController(this.EyesController);
    }

    private static Exception rethrow(Exception exception) {
        return exception;
    }

    public static class EventHandler {
        HashSet<EntityPlayer> TrackedPlayers = new HashSet();

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void onRenderHand(RenderHandEvent renderHandEvent) {
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer((EntityPlayer)Minecraft.getMinecraft().player);
            try {
                if (playerGirl == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            try {
                if (!(playerGirl instanceof GirlMaster)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            try {
                if (((GirlMaster)((Object)playerGirl)).e() != null) {
                    renderHandEvent.setCanceled(true);
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
        }

        @SubscribeEvent
        public void a(TickEvent.PlayerTickEvent playerTickEvent) {
            EntityPlayer entityPlayer = playerTickEvent.player;
            try {
                if (entityPlayer == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            this.a(entityPlayer);
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(TickEvent.RenderTickEvent renderTickEvent) {
            try {
                if (renderTickEvent.phase == TickEvent.Phase.END) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            EntityPlayerSP entityPlayerSP = Minecraft.getMinecraft().player;
            try {
                if (entityPlayerSP == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            this.a((EntityPlayer)entityPlayerSP);
        }

        void a(EntityPlayer entityPlayer) {
            PlayerGirlEntity playerGirl;
            block16: {
                playerGirl = PlayerGirlEntity.getByPlayer(entityPlayer);
                try {
                    if (!(playerGirl instanceof GoblinPlayer)) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                }
                GirlAnimationState girlAnimationState = playerGirl.getCurrentAction();
                try {
                    if (girlAnimationState == GirlAnimationState.THROWN) {
                        return;
                    }
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                }
                try {
                    try {
                        if (girlAnimationState != GirlAnimationState.START_THROWING || ((GirlMaster)((Object)playerGirl)).a() <= 15) break block16;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                    }
                    return;
                }
                catch (RuntimeException runtimeException) {
                    throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                }
            }
            UUID uUID = ((GoblinPlayer)playerGirl).e();
            try {
                if (uUID == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            EntityPlayer entityPlayer2 = entityPlayer.world.getPlayerEntityByUUID(uUID);
            try {
                if (entityPlayer2 == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            entityPlayer.noClip = true;
            entityPlayer.setNoGravity(true);
            playerGirl.noClip = true;
            playerGirl.setNoGravity(true);
            entityPlayer.setPosition(entityPlayer2.posX, entityPlayer2.posY + 2.0, entityPlayer2.posZ);
            entityPlayer.lastTickPosX = entityPlayer2.lastTickPosX;
            entityPlayer.lastTickPosY = entityPlayer2.lastTickPosY + 2.0;
            entityPlayer.lastTickPosZ = entityPlayer2.lastTickPosZ;
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void a(RenderWorldLastEvent renderWorldLastEvent) {
            Minecraft minecraft = Minecraft.getMinecraft();
            RenderManager renderManager = minecraft.getRenderManager();
            EntityPlayerSP entityPlayerSP = minecraft.player;
            try {
                if (minecraft.player == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            Vec3d vec3d = entityPlayerSP.getPositionVector();
            for (EntityPlayer entityPlayer : this.TrackedPlayers) {
                Vec3d vec3d2 = entityPlayer.getPositionVector();
                Vec3d vec3d3 = vec3d2.subtract(vec3d);
                renderManager.renderEntity((Entity)entityPlayer, vec3d3.x, vec3d3.y, vec3d3.z, 69.0f, renderWorldLastEvent.getPartialTicks(), true);
            }
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
        }

        @SideOnly(value=Side.CLIENT)
        @SubscribeEvent
        public void b(TickEvent.RenderTickEvent renderTickEvent) {
            block3: {
                block2: {
                    try {
                        if (renderTickEvent.phase != TickEvent.Phase.START) break block2;
                        this.checkHomeData();
                        break block3;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                    }
                }
                this.resetHomeData();
            }
        }

        @SideOnly(value=Side.CLIENT)
        void a() {
            for (EntityPlayer entityPlayer : this.TrackedPlayers) {
                entityPlayer.isDead = true;
            }
        }

        @SideOnly(value=Side.CLIENT)
        void b() {
            this.TrackedPlayers.clear();
            Minecraft minecraft = Minecraft.getMinecraft();
            EntityPlayerSP entityPlayerSP = minecraft.player;
            try {
                if (minecraft.world == null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            for (EntityPlayer entityPlayer : minecraft.world.playerEntities) {
                block17: {
                    try {
                        if (entityPlayer == entityPlayerSP) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                    }
                    PlayerGirlEntity playerGirl = PlayerGirlEntity.getByPlayer(entityPlayer);
                    try {
                        if (!(playerGirl instanceof GoblinPlayer)) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                    }
                    GoblinPlayer goblin = (GoblinPlayer)playerGirl;
                    try {
                        if (goblin.e() == null) {
                            continue;
                        }
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                    }
                    GirlAnimationState girlAnimationState = goblin.getCurrentAction();
                    try {
                        try {
                            if (girlAnimationState != GirlAnimationState.THROWN && girlAnimationState != GirlAnimationState.START_THROWING) break block17;
                        }
                        catch (RuntimeException runtimeException) {
                            throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                        }
                        return;
                    }
                    catch (RuntimeException runtimeException) {
                        throw GoblinPlayer.EventHandler.rethrow(runtimeException);
                    }
                }
                this.TrackedPlayers.add(entityPlayer);
                entityPlayer.isDead = false;
            }
        }

        @SubscribeEvent
        public void a(PlayerInteractEvent.EntityInteract entityInteract) {
            EntityPlayer entityPlayer = entityInteract.getEntityPlayer();
            try {
                if (!entityPlayer.isSneaking()) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            try {
                if (!(entityInteract.getTarget() instanceof EntityPlayer)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            PlayerGirlEntity playerGirl = PlayerGirlEntity.getByUuid(entityInteract.getTarget().getPersistentID());
            try {
                if (!(playerGirl instanceof GoblinPlayer)) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            PlayerGirlEntity playerGirl2 = PlayerGirlEntity.getByUuid(entityPlayer.getPersistentID());
            try {
                if (playerGirl2 != null) {
                    return;
                }
            }
            catch (RuntimeException runtimeException) {
                throw GoblinPlayer.EventHandler.rethrow(runtimeException);
            }
            ((GoblinPlayer)playerGirl).c(entityInteract.getEntityPlayer());
        }

        private static RuntimeException rethrow(RuntimeException runtimeException) {
            return runtimeException;
        }
    }
}

