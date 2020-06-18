import com.jb.flieUtlis.OSSFileImp;

import java.io.File;

public class upload {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\93715\\Desktop\\手册.md");
        String s = new OSSFileImp().ossFileUpload(file,"手册.md");
        System.out.println(s);
    }
}
