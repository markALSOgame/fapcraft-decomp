package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBuf;
import java.util.ConcurrentModificationException;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdatePlayerModel implements IMessage {
   boolean Loaded = false;
   GirlRegistry GirlType;

   public PacketUpdatePlayerModel() {
   }

   public PacketUpdatePlayerModel(GirlRegistry girlType) {
      this.GirlType = girlType;
   }

   public void fromBytes(ByteBuf buf) {
      String string = ByteBufUtils.readUTF8String(buf);

      label17: {
         try {
            if ("player".equals(string)) {
               this.GirlType = null;
               break label17;
            }
         } catch (RuntimeException error) {
            throw rethrow(error);
         }

         this.GirlType = GirlRegistry.valueOf(string);
      }

      this.Loaded = true;
   }

   public void toBytes(ByteBuf buf) {
      try {
         if (this.GirlType == null) {
            ByteBufUtils.writeUTF8String(buf, "player");
            return;
         }
      } catch (RuntimeException error) {
         throw rethrow(error);
      }

      ByteBufUtils.writeUTF8String(buf, this.GirlType.toString());
   }

   private static RuntimeException rethrow(RuntimeException error) {
      return error;
   }

   public static class Handler implements IMessageHandler<PacketUpdatePlayerModel, IMessage> {

      public IMessage handle(PacketUpdatePlayerModel packet, MessageContext ctx) {
            block4: {
                try {
                    try {
                        if (packet.Loaded && ctx.side == Side.SERVER) break block4;
                    }
                    catch (ConcurrentModificationException concurrentModificationException) {
                        throw PacketUpdatePlayerModel.Handler.rethrow(concurrentModificationException);
                    }
                    System.out.println("received an invalid message @UpdatePlayerModel :(");
                    return null;
                }
                catch (ConcurrentModificationException concurrentModificationException) {
                    throw PacketUpdatePlayerModel.Handler.rethrow(concurrentModificationException);
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

      private static ConcurrentModificationException rethrow(ConcurrentModificationException error) {
         return error;
      }
   }
}
