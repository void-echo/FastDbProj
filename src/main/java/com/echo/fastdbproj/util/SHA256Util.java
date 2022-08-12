package com.echo.fastdbproj.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class SHA256Util {
    public String sha256(@NotNull String stringText) {
        return DigestUtils.sha256Hex(stringText);
    }

    public boolean checkPlain(@NotNull String plain, @NotNull String sha256) {
        return sha256(plain).equals(sha256);
    }

    public boolean checkSHA(@NotNull String shaA, @NotNull String shaB) {
        return shaA.equals(shaB);
    }

    public static void main(String[] args) {
        String str = "123123";
        String str2 = "123124";
        var sha = new SHA256Util();
        var m = "96cae35ce8a9b0244178bf28e4966c2ce1b8385723a96a6b838858cdd6ca0a1e";
        System.out.println(sha.sha256(str));
        System.out.println(sha.sha256(str2));
    }
}
