import java.util.HashMap;

public class instructions {
    HashMap<String, String> r_type = new HashMap<String, String>();
    HashMap<String, String> i_type = new HashMap<String, String>();
    HashMap<String, String> j_type = new HashMap<String, String>();

    public instructions(){
        r_type.put("add","100000");
        r_type.put("slt","101010");
        r_type.put("sll","000000");
        r_type.put("jr","001000");
        r_type.put("move","100101");

        i_type.put("lw","100011");
        i_type.put("sw","101011");
        i_type.put("slti","001010");
        i_type.put("addi","001000");
        i_type.put("beq","000100");
        i_type.put("bne","000101");

        j_type.put("j","000010");
        j_type.put("jal","000011");
    }

}
