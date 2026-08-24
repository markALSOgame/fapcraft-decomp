package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketStartStandingSexAnimation implements IMessage {
   boolean Loaded;
   UUID PlayerUuid;
   UUID GirlUuid;
   String AnimationName;

   public PacketStartStandingSexAnimation() {
   }

   public PacketStartStandingSexAnimation(UUID uuid, UUID uuid2, String string) {
      this.PlayerUuid = uuid;
      this.GirlUuid = uuid2;
      this.AnimationName = string;
   }

   public void fromBytes(ByteBuf buf) {
      this.PlayerUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.AnimationName = ByteBufUtils.readUTF8String(buf);
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBufUtils.writeUTF8String(buf, this.PlayerUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
      ByteBufUtils.writeUTF8String(buf, this.AnimationName);
   }

   public static class Handler implements IMessageHandler<PacketStartStandingSexAnimation, IMessage> {

      public IMessage handle(PacketStartStandingSexAnimation packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (RuntimeException runtimeException) {
                        throw PacketStartStandingSexAnimation.Handler.rethrow(runtimeException);
                    }
                    System.out.println("received an invalid message @StartStandingSexAnimation :(");
                    return null;
                }
                catch (RuntimeException runtimeException) {
                    throw PacketStartStandingSexAnimation.Handler.rethrow(runtimeException);
                }
            }
            FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> {
                /*
                 * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
                 * 
                 * org.benf.cfr.reader.util.ConfusedCFRException: Started 3 blocks at once
                 *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:412)
                 *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:487)
                 *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
                 *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
                 *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
                 *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
                 *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
                 *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1050)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
                 *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
                 *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
                 *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
                 *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
                 *     at org.benf.cfr.reader.Main.main(Main.java:54)
                 */
                throw new IllegalStateException("Decompilation failed");
            });
            return null;
        }

      private static Exception rethrow(Exception error) {
         return error;
      }
   }
}
