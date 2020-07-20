package com.oddcc.learn.memorylayout;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class MemoryLayoutUnitTest {
    @Test
    void printVMDetails() {
        System.out.println(VM.current().details());
    }

    @Test
    void printSimpleIntClass() {
        System.out.println(ClassLayout.parseClass(SimpleInt.class).toPrintable());
    }

    @Test
    void printSimpleIntInstance() {
        SimpleInt instance = new SimpleInt();
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    }

    @Test
    void printSimpleIntInstanceHashCode() {
        SimpleInt instance = new SimpleInt();
        System.out.println(instance.hashCode());
        System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    }

    @Test
    void printAlignmentSimpleIntClass() {
        System.out.println(ClassLayout.parseClass(AlignmentSimpleInt.class).toPrintable());
    }

    @Test
    void printFieldsArrangement() {
        System.out.println(ClassLayout.parseClass(FieldsArrangement.class).toPrintable());
    }

    @Test
    void printSimpleLock() {
        SimpleLock simpleLock = new SimpleLock();
        System.out.println(ClassLayout.parseInstance(simpleLock).toPrintable());
    }

    @Test
    void synPrintSimpleLock() {
        SimpleLock simpleLock = new SimpleLock();
        synchronized (simpleLock) {
            System.out.println(ClassLayout.parseInstance(simpleLock).toPrintable());
        }
    }

    private volatile Object consumer;

    @Test
    void printGCInfo() {
        Object instance = new Object();
        long lastAddr = VM.current().addressOf(instance);
        ClassLayout layout = ClassLayout.parseInstance(instance);
        System.out.println("start looping...");
        for (int i = 0; i < 10_000; i++) {
            long currentAddr = VM.current().addressOf(instance);
            if (currentAddr != lastAddr) {
                System.out.println(layout.toPrintable());
            }

            // create lots of garbage
            for (int j = 0; j < 10_000; j++) {
                consumer = new Object();
            }

            lastAddr = currentAddr;
        }
    }
}
