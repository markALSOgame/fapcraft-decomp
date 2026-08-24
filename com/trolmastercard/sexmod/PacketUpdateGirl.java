package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketUpdateGirl implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   String Key;
   String Value;

   public PacketUpdateGirl() {
      this.Loaded = false;
   }

   public PacketUpdateGirl(UUID uuid, String string, String string2) {
      this.GirlUuid = uuid;
      this.Key = string;
      this.Value = string2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Key = ByteBufUtils.readUTF8String(buf);
      this.Value = ByteBufUtils.readUTF8String(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBuf buf2;
      String string;
      label16: {
         try {
            ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
            ByteBufUtils.writeUTF8String(buf, this.Key);
            buf2 = buf;
            if (this.Value == null) {
               string = "null";
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         string = this.Value;
      }

      ByteBufUtils.writeUTF8String(buf2, string);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

// $VF: Couldn't be decompiled
// Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
// java.lang.NullPointerException: Cannot invoke "org.jetbrains.java.decompiler.modules.decompiler.stats.Statement.getVarDefinitions()" because "stat" is null
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1592)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingExprent(VarDefinitionHelper.java:1835)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingExprent(VarDefinitionHelper.java:2029)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1619)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1739)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.iterateClashingNames(VarDefinitionHelper.java:1739)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarDefinitionHelper.remapClashingNames(VarDefinitionHelper.java:1584)
//   at org.jetbrains.java.decompiler.modules.decompiler.vars.VarProcessor.rerunClashing(VarProcessor.java:99)
//   at org.jetbrains.java.decompiler.main.ClassWriter.invokeProcessors(ClassWriter.java:145)
//   at org.jetbrains.java.decompiler.main.ClassWriter.writeClass(ClassWriter.java:379)
//   at org.jetbrains.java.decompiler.main.ClassWriter.writeClass(ClassWriter.java:520)
//   at org.jetbrains.java.decompiler.main.ClassesProcessor.writeClass(ClassesProcessor.java:521)
//   at org.jetbrains.java.decompiler.main.Fernflower.getClassContent(Fernflower.java:200)
//   at org.jetbrains.java.decompiler.struct.ContextUnit.lambda$save$3(ContextUnit.java:221)
}
