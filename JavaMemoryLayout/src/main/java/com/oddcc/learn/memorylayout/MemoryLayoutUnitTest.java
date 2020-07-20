package com.oddcc.learn.memorylayout;

import org.junit.jupiter.api.Test;
import org.openjdk.jol.vm.VM;

public class MemoryLayoutUnitTest {
    private volatile Object obj;

    @Test
    public void printVMDetails() {
        System.out.println(VM.current().details());
    }
}
