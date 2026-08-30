package com.trolmastercard.sexmod;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.Channel.Unsafe;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.net.SocketAddress;
import net.minecraft.network.EnumPacketDirection;
import net.minecraft.network.NetworkManager;

public class NullNetworkManager extends NetworkManager {
   public NullNetworkManager(EnumPacketDirection enumPacketDirection) {
      super(enumPacketDirection);
   }

   public Channel channel() {
      return new Channel() {
         public ChannelId id() {
            return null;
         }

         public EventLoop eventLoop() {
            return null;
         }

         public Channel parent() {
            return null;
         }

         public ChannelConfig config() {
            return null;
         }

         public boolean isOpen() {
            return false;
         }

         public boolean isRegistered() {
            return false;
         }

         public boolean isActive() {
            return false;
         }

         public ChannelMetadata metadata() {
            return null;
         }

         public SocketAddress localAddress() {
            return null;
         }

         public SocketAddress remoteAddress() {
            return null;
         }

         public ChannelFuture closeFuture() {
            return null;
         }

         public boolean isWritable() {
            return false;
         }

         public long bytesBeforeUnwritable() {
            return 0L;
         }

         public long bytesBeforeWritable() {
            return 0L;
         }

         public Unsafe unsafe() {
            return null;
         }

         public ChannelPipeline pipeline() {
            return null;
         }

         public ByteBufAllocator alloc() {
            return null;
         }

         public ChannelPromise newPromise() {
            return null;
         }

         public ChannelProgressivePromise newProgressivePromise() {
            return null;
         }

         public ChannelFuture newSucceededFuture() {
            return null;
         }

         public ChannelFuture newFailedFuture(Throwable error) {
            return null;
         }

         public ChannelPromise voidPromise() {
            return null;
         }

         public ChannelFuture bind(SocketAddress socketAddress) {
            return null;
         }

         public ChannelFuture connect(SocketAddress socketAddress2) {
            return null;
         }

         public ChannelFuture connect(SocketAddress socketAddress3, SocketAddress socketAddress4) {
            return null;
         }

         public ChannelFuture disconnect() {
            return null;
         }

         public ChannelFuture close() {
            return null;
         }

         public ChannelFuture deregister() {
            return null;
         }

         public ChannelFuture bind(SocketAddress socketAddress5, ChannelPromise channelPromise) {
            return null;
         }

         public ChannelFuture connect(SocketAddress socketAddress6, ChannelPromise channelPromise2) {
            return null;
         }

         public ChannelFuture connect(SocketAddress socketAddress7, SocketAddress socketAddress8, ChannelPromise channelPromise3) {
            return null;
         }

         public ChannelFuture disconnect(ChannelPromise channelPromise4) {
            return null;
         }

         public ChannelFuture close(ChannelPromise channelPromise5) {
            return null;
         }

         public ChannelFuture deregister(ChannelPromise channelPromise6) {
            return null;
         }

         public Channel read() {
            return null;
         }

         public ChannelFuture write(Object obj) {
            return null;
         }

         public ChannelFuture write(Object obj2, ChannelPromise channelPromise7) {
            return null;
         }

         public Channel flush() {
            return null;
         }

         public ChannelFuture writeAndFlush(Object obj3, ChannelPromise channelPromise8) {
            return null;
         }

         public ChannelFuture writeAndFlush(Object obj4) {
            return null;
         }

         public <T> Attribute<T> attr(AttributeKey<T> attributeKey) {
            return new Attribute<T>() {
               public T setIfAbsent(T t) {
                  return null;
               }

               public T getAndSet(T t2) {
                  return null;
               }

               public AttributeKey<T> key() {
                  return null;
               }

               public T getAndRemove() {
                  return null;
               }

               public void remove() {
               }

               public T get() {
                  return null;
               }

               public boolean compareAndSet(T t3, T t4) {
                  return false;
               }

               public void set(T t5) {
               }
            };
         }

         public <T> boolean hasAttr(AttributeKey<T> attributeKey2) {
            return false;
         }

         public int compareTo(Channel channel) {
            return 0;
         }
      };
   }
}
