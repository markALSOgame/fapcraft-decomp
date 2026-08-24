package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.UUID;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSexPromptReply implements IMessage {
   boolean Loaded;
   UUID GirlUuid;
   boolean Accept;
   boolean Reject;
   UUID PlayerUuid = null;

   public PacketSexPromptReply() {
      this.Loaded = false;
   }

   public PacketSexPromptReply(UUID uuid, UUID uuid2, boolean flag, boolean flag2) {
      this.GirlUuid = uuid;
      this.Accept = flag;
      this.PlayerUuid = uuid2;
      this.Reject = flag2;
      this.Loaded = true;
   }

   public void fromBytes(ByteBuf buf) {
      this.GirlUuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
      this.Accept = buf.readBoolean();
      this.Reject = buf.readBoolean();
      String string = ByteBufUtils.readUTF8String(buf);

      PacketSexPromptReply packet;
      UUID uuid;
      label17: {
         try {
            packet = this;
            if (string.equals("null")) {
               uuid = null;
               break label17;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         uuid = UUID.fromString(string);
      }

      packet.PlayerUuid = uuid;
      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      ByteBuf buf2;
      String string;
      label16: {
         try {
            ByteBufUtils.writeUTF8String(buf, this.GirlUuid.toString());
            buf.writeBoolean(this.Accept);
            buf.writeBoolean(this.Reject);
            buf2 = buf;
            if (this.PlayerUuid == null) {
               string = "null";
               break label16;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         string = this.PlayerUuid.toString();
      }

      ByteBufUtils.writeUTF8String(buf2, string);
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketSexPromptReply, IMessage> {

      public static void handle(UUID uuid, UUID uuid2, boolean flag, boolean flag2) {
            /*
             * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
             * 
             * org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 17[WHILELOOP]
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:435)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:484)
             *     at org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:736)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:850)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:278)
             *     at org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:201)
             *     at org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
             *     at org.benf.cfr.reader.entities.Method.analyse(Method.java:531)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1055)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:923)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
             *     at org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:942)
             *     at org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:257)
             *     at org.benf.cfr.reader.Driver.doJar(Driver.java:139)
             *     at org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:76)
             *     at org.benf.cfr.reader.Main.main(Main.java:54)
             */
            throw new IllegalStateException("Decompilation failed");
        }


      public IMessage handle(PacketSexPromptReply packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (!packet.Loaded || ctx.side != Side.SERVER) break block4;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PacketSexPromptReply.Handler.rethrow(concurrentModificationException);
                    }
                    FMLCommonHandler.instance().getMinecraftServerInstance().addScheduledTask(() -> PacketSexPromptReply.Handler.handle(packet.GirlUuid, packet.PlayerUuid, packet.Accept, packet.Reject));
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PacketSexPromptReply.Handler.rethrow(concurrentModificationException);
                }
            }
            return null;
        }

      private static RuntimeException rethrow(RuntimeException error) {
         return error;
      }
   }
}
