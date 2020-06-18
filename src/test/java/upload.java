import com.jb.flieUtlis.OSSFileImp;

import java.io.File;

public class upload {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\93715\\Desktop\\README.md");
        String s = new OSSFileImp().ossFileUpload(file,"README.md");
        System.out.println(s);
    }
}
