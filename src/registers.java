import java.util.HashMap;

public class registers {
    HashMap<String, String> names = new HashMap<String, String>();

    public registers(){
        names.put("$zero", "$0");
        names.put("$at", "$1");
        names.put("$v0", "$2");
        names.put("$v1", "$3");
        names.put("$a0", "$4");
        names.put("$a1", "$5");
        names.put("$a2", "$6");
        names.put("$a3", "$7");
        names.put("$t0", "$8");
        names.put("$t1", "$9");
        names.put("$t2", "$10");
        names.put("$t3", "$11");
        names.put("$t4", "$12");
        names.put("$t5", "$13");
        names.put("$t6", "$14");
        names.put("$t7", "$15");
        names.put("$s0", "$16");
        names.put("$s1", "$17");
        names.put("$s2", "$18");
        names.put("$s3", "$19");
        names.put("$s4", "$20");
        names.put("$s5", "$21");
        names.put("$s6", "$22");
        names.put("$s7", "$23");
        names.put("$t8", "$24");
        names.put("$t9", "$25");
        names.put("$k0", "$26");
        names.put("$k1", "$27");
        names.put("$gp", "$28");
        names.put("$sp", "$29");
        names.put("$fp", "$30");
        names.put("$ra", "$31");
    }
}
