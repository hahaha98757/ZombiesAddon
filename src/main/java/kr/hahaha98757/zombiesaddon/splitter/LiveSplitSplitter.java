package kr.hahaha98757.zombiesaddon.splitter;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface LiveSplitSplitter {

	CompletableFuture<Void> startOrSplit();

}