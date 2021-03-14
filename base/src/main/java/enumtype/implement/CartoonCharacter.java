package enumtype.implement;

import generics.Generator;

import java.util.Random;

public enum CartoonCharacter implements Generator<CartoonCharacter> {

    SLAPPY, SPANKY, PUNCHY;

    private Random rand = new Random(47);
    public CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}
