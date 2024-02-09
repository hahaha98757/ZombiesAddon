package kr.hahaha98757.zombiesaddon.handler;

import kr.hahaha98757.zombiesaddon.netty.NettyPacketHandler;
import kr.hahaha98757.zombiesaddon.packet.PacketInterceptor;
import io.netty.channel.ChannelHandler;

import java.util.Objects;

public class PacketClientConnectHandler extends ClientConnectHandler {

	private final Iterable<PacketInterceptor> interceptors;

	public PacketClientConnectHandler(Iterable<PacketInterceptor> interceptors) {
		this.interceptors = Objects.requireNonNull(interceptors, "interceptors");
	}

	@Override
	protected ChannelHandler createChannelHandler() {
		return new NettyPacketHandler(interceptors);
	}

}