package kr.hahaha98757.zombiesaddon.tweaker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class SpawnTimeRendererTransformer implements IClassTransformer {
	private final Logger LOGGER = LogManager.getLogger("SSTRounds");

	public SpawnTimeRendererTransformer() {
	}

	public byte[] transform(String name, String transformedName, byte[] bytes) {
		if (!isTransformer()) {
			return null;
		}
		if (bytes == null) {
			return null;
		} else if (!transformedName.equals("com.seosean.showspawntime.features.spawntimes.SpawnTimeRenderer")) {
			return bytes;
		} else {
			ClassReader reader = new ClassReader(bytes);
			ClassNode node = new ClassNode();
			reader.accept(node, 8);
			Iterator var6 = node.methods.iterator();

			while (var6.hasNext()) {
				MethodNode methodNode = (MethodNode) var6.next();
				String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(node.name, methodNode.name,
						methodNode.desc);
				if (methodName.equals("onRender")) {
					methodNode.instructions.insertBefore(methodNode.instructions.getFirst(), this.addCode());
					break;
				}
			}

			ClassWriter writer = new ClassWriter(2);

			try {
				node.accept(writer);
			} catch (Throwable var9) {
				this.LOGGER.error("Exception when transforming {} : {}",
						new Object[] { transformedName, var9.getClass().getSimpleName() });
				var9.printStackTrace();
			}

			return writer.toByteArray();
		}
	}

	private InsnList addCode() {
		InsnList list = new InsnList();
		list.add(new InsnNode(Opcodes.RETURN));
		return list;
	}

	public static boolean isTransformer() {
		try {
			return ExtraSetting.readFile(1).equals("true");
		} catch (Exception e) {
			return true;
		}
	}
}
