package io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @ClassName TransferTo
 * @Author bin
 * @Date 2020/7/27 下午5:08
 * @Decr TODO
 *          转换操作。将两个通道相连接
 * @Link TODO
 **/
public class TransferTo {
    public static void main(String[] args) throws IOException {
        String path = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data.txt";
        String path2 = "/Users/bin/gitStudy/code_diary/base/src/main/resources/data2.txt";
        FileChannel in = new FileInputStream(path).getChannel(),
                out = new FileOutputStream(path2).getChannel();

        in.transferTo(0,in.size(),out);
        out.transferFrom(in,0,in.size());
    }
}
