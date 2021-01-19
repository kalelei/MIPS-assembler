import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class Assembler {
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        String[] swap = new String[2], sort = new String[2], for1tst = new String[2], for2tst = new String[2], exit1 = new String[2], exit2 = new String[2];
        String address = "80001000"; // starting address
        String address_hold = "80001000"; // to hold for every instruction
        int choose;
        do{
            System.out.print("\nInteractive Mod (1) / Batch Mode (2) / EXIT (-1) \nPlease enter the number of the mod:");
            choose = myObj.nextInt();
            switch (choose){
                case 1:{
                    int first;
                    String op = "", shamt = "", func = "", offValue = "";
                    String rs, rt, rd;
                    System.out.println("Please enter your instructions: (add $s1, $s2, $s3)");
                    myObj.nextLine();
                    String inst_code = myObj.nextLine();
                    inst_code = inst_code.replaceAll("(, )|[(]", " ");         // we are taking the input here and getting rid of the ", "  and "()"
                    inst_code = inst_code.replaceAll("[)]", "");
                    String[] split = inst_code.split(" ");
                    instructions inst = new instructions();
                    try{
                        if (split[0].equals("add") || split[0].equals("slt")) {
                            shamt = "00000";
                            op = "000000";
                            if (split[0].equals("add")) {
                                func = inst.r_type.get(split[0]);
                            } else if (split[0].equals("slt")) {
                                func = inst.r_type.get(split[0]);
                            }
                            registers r1 = new registers();
                            registers r2 = new registers();
                            registers r3 = new registers();
                            rs = r1.names.get(split[2]);
                            rt = r2.names.get(split[3]);
                            rd = r3.names.get(split[1]);
                            rs = rs.replaceAll("[$]", "");
                            rt = rt.replaceAll("[$]", "");
                            rd = rd.replaceAll("[$]", "");
                            rs = Integer.toBinaryString(Integer.parseInt(rs));
                            rt = Integer.toBinaryString(Integer.parseInt(rt));
                            rd = Integer.toBinaryString(Integer.parseInt(rd));

                            int l = 0;
                            if (rs.length() < 5) {
                                l = 5 - rs.length();
                                rs = "0".repeat(l) + rs;
                            }
                            l = 0;
                            if (rd.length() < 5) {
                                l = 5 - rd.length();                             // we make sure rs, rt, rd are 5 bit everytime
                                rd = "0".repeat(l) + rd;
                            }
                            l = 0;
                            if (rt.length() < 5) {
                                l = 5 - rt.length();
                                rt = "0".repeat(l) + rt;
                            }

                            String add = op + rs + rt + rd + shamt + func;

                            String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                            System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                            System.out.println("Ox" + hexadd);
                        } else if (split[0].equals("sll")) {
                            op = "000000";
                            func = inst.r_type.get(split[0]);
                            rs = "00000";
                            registers r2 = new registers();
                            registers r3 = new registers();
                            shamt = Integer.toBinaryString(Integer.parseInt(split[3]));
                            rt = r2.names.get(split[2]);
                            rd = r3.names.get(split[1]);
                            rt = rt.replaceAll("[$]", "");
                            rd = rd.replaceAll("[$]", "");
                            rt = Integer.toBinaryString(Integer.parseInt(rt));
                            rd = Integer.toBinaryString(Integer.parseInt(rd));

                            int l = 0;
                            if (rd.length() < 5) {
                                l = 5 - rd.length();                             // we make sure rs, rt, rd are 5 bit everytime
                                rd = "0".repeat(l) + rd;
                            }
                            l = 0;
                            if (rt.length() < 5) {
                                l = 5 - rt.length();
                                rt = "0".repeat(l) + rt;
                            }
                            l = 0;
                            if (shamt.length() < 5) {
                                l = 5 - shamt.length();
                                shamt = "0".repeat(l) + shamt;
                            }

                            String add = op + rs + rt + rd + shamt + func;

                            String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                            System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                            System.out.println("Ox" + hexadd);
                        }


                        if (split[0].equals("lw") || split[0].equals("sw")) {
                            if (split[0].equals("lw")) {
                                op = inst.i_type.get(split[0]);
                            } else if (split[0].equals("sw")) {
                                op = inst.i_type.get(split[0]);
                            }

                            registers r1 = new registers();
                            registers r2 = new registers();
                            rs = r1.names.get(split[3]);
                            rt = r2.names.get(split[1]);
                            offValue = Integer.toBinaryString(Integer.parseInt(split[2]));
                            rs = rs.replaceAll("[$]", "");
                            rt = rt.replaceAll("[$]", "");
                            rs = Integer.toBinaryString(Integer.parseInt(rs));
                            rt = Integer.toBinaryString(Integer.parseInt(rt));

                            int l = 0;
                            if (rs.length() < 5) {
                                l = 5 - rs.length();                  // we make sure rs, rt, rd are 5 bit everytime
                                rs = "0".repeat(l) + rs;
                            }
                            l = 0;
                            if (rt.length() < 5) {
                                l = 5 - rt.length();
                                rt = "0".repeat(l) + rt;
                            }
                            l = 0;
                            if (offValue.length() < 16) {
                                l = 16 - offValue.length();
                                offValue = "0".repeat(l) + offValue;
                            }

                            String add = op + rs + rt + offValue;

                            String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                            System.out.println(op + "/" + rs + "/" + rt + "/" + offValue);
                            System.out.println("Ox" + hexadd);

                        } else if (split[0].equals("addi") || split[0].equals("slti")) {
                            if (split[0].equals("addi")) {
                                op = inst.i_type.get(split[0]);
                            } else if (split[0].equals("slti")) {
                                op = inst.i_type.get(split[0]);
                            }
                            registers r1 = new registers();
                            registers r2 = new registers();
                            rs = r1.names.get(split[2]);
                            rt = r2.names.get(split[1]);
                            offValue = Integer.toBinaryString(Integer.parseInt(split[3]));
                            rs = rs.replaceAll("[$]", "");
                            rt = rt.replaceAll("[$]", "");
                            rs = Integer.toBinaryString(Integer.parseInt(rs));
                            rt = Integer.toBinaryString(Integer.parseInt(rt));

                            int l = 0;
                            if (rs.length() < 5) {
                                l = 5 - rs.length();                  // we make sure rs, rt, rd are 5 bit everytime
                                rs = "0".repeat(l) + rs;
                            }
                            l = 0;
                            if (rt.length() < 5) {
                                l = 5 - rt.length();
                                rt = "0".repeat(l) + rt;
                            }
                            l = 0;
                            if (offValue.length() < 16) {
                                l = 16 - offValue.length();
                                offValue = "0".repeat(l) + offValue;
                            }
                            if (offValue.length() >16) {
                                offValue = offValue.substring(16,32);
                            }

                            String add = op + rs + rt + offValue;

                            String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                            System.out.println(op + "/" + rs + "/" + rt + "/" + offValue);
                            System.out.println("Ox" + hexadd);
                        } else if (split[0].equals("beq") || split[0].equals("bne")) {
                            if (split[0].equals("beq")) {
                                op = inst.i_type.get(split[0]);
                            } else if (split[0].equals("bne")) {
                                op = inst.i_type.get(split[0]);
                            }
                            registers r1 = new registers();
                            registers r2 = new registers();
                            rs = r1.names.get(split[2]);
                            rt = r2.names.get(split[1]);
                            offValue = Integer.toBinaryString(Integer.parseInt(split[3]));
                            rs = rs.replaceAll("[$]", "");
                            rt = rt.replaceAll("[$]", "");
                            rs = Integer.toBinaryString(Integer.parseInt(rs));
                            rt = Integer.toBinaryString(Integer.parseInt(rt));

                            int l = 0;
                            if (rs.length() < 5) {
                                l = 5 - rs.length();                  // we make sure rs, rt, rd are 5 bit everytime
                                rs = "0".repeat(l) + rs;
                            }
                            l = 0;
                            if (rt.length() < 5) {
                                l = 5 - rt.length();
                                rt = "0".repeat(l) + rt;
                            }
                            l = 0;
                            if (offValue.length() < 16) {
                                l = 16 - offValue.length();
                                offValue = "0".repeat(l) + offValue;
                            }

                            String add = op + rs + rt + offValue;

                            String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                            System.out.println(op + "/" + rs + "/" + rt + "/" + offValue);
                            System.out.println("Ox" + hexadd);
                        }
                        else if (split[0].equals("move")) {
                            shamt = "00000";
                            op = "000000";
                            rt = "00000";
                            func = inst.r_type.get(split[0]);            //move $1, $2 translates to add $1, $2, $0
                            registers r1 = new registers();
                            registers r2 = new registers();
                            rd = r1.names.get(split[1]);
                            rs = r2.names.get(split[2]);
                            rd = rd.replaceAll("[$]", "");
                            rs = rs.replaceAll("[$]", "");
                            rd = Integer.toBinaryString(Integer.parseInt(rd));
                            rs = Integer.toBinaryString(Integer.parseInt(rs));

                            int l = 0;
                            if (rs.length() < 5) {
                                l = 5 - rs.length();
                                rs = "0".repeat(l) + rs;
                            }
                            l = 0;
                            if (rd.length() < 5) {          // we make sure rs, rt, rd are 5 bit everytime
                                l = 5 - rd.length();
                                rd = "0".repeat(l) + rd;
                            }

                            String add = op + rs + rt + rd + shamt + func;

                            String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                            System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                            System.out.println("Ox" + hexadd);
                        }
                        else if (!(split[0].equals("add") || split[0].equals("slt") || split[0].equals("lw") || split[0].equals("sw") || split[0].equals("addi") || split[0].equals("slti"))) {
                            System.out.println("There is no such instruction");
                        }
                    } catch (Exception e){
                        e.printStackTrace();

                        System.out.println(e);
                        System.out.println("Wrong Input Format\nif something is null please check if you add $ for the registers");
                    }
                    break;
                }



                case 2: {
                    File myFile = new File("corg.src");
                    Scanner myReader = null;
                    String data;
                    String[] split;
                    swap[1] = "0";
                    sort[1] = "0";
                    for1tst[1] = "0";
                    for2tst[1] = "0";
                    exit1[1] = "0";
                    exit2[1] = "0";
                    FileOutputStream fos = new FileOutputStream("batch.obj");
                    OutputStreamWriter fout = new OutputStreamWriter(fos);
                    try {
                        myReader = new Scanner(myFile);
                        while (myReader.hasNextLine()) {
                             data = myReader.nextLine();  // we are taking the lines one by one from the file


                            data = data.replaceAll("(, )|[(]|(: )", " ");         // we are taking the input here and getting rid of the ,  and ()
                            data = data.replaceAll("[)]", "");


                            split = data.split(" ");

                            int i;





                            if (split[0].equals("swap") || split[0].equals("sort") || split[0].equals("for1tst") || split[0].equals("for2tst") || split[0].equals("exit1") || split[0].equals("exit2")) {
                                if (swap[1].equals("0")) {
                                    swap[0] = address;
                                    swap[1] = "1";
                                } else if (sort[1].equals("0")) {
                                    sort[0] = address;
                                    sort[1] = "1";
                                } else if (for1tst[1].equals("0")) {
                                    for1tst[0] = address;
                                    for1tst[1] = "1";
                                } else if (for2tst[1].equals("0")) {
                                    for2tst[0] = address;
                                    for2tst[1] = "1";
                                } else if (exit2[1].equals("0")) {
                                    exit2[0] = address;
                                    exit2[1] = "1";
                                } else if (exit1[1].equals("0")) {
                                    exit1[0] = address;
                                    exit1[1] = "1";
                                }
                            }
                            Long value = Long.parseLong(address, 16);            // we are changing the address by adding +1 to hexa form
                            value+=4;
                            address = Long.toHexString(value);



                        }
                        System.out.println("\nAddresses of labels: \n" + "swap: " + swap[0] + " sort: " + sort[0] + " frt1: " + for1tst[0] + " frt2: " + for2tst[0] + " exit1: " + exit1[0] + " exit2: " + exit2[0]);
                        myReader.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    try {
                        myReader = new Scanner(myFile);
                        while (myReader.hasNextLine()) {
                            data = myReader.nextLine();  // we are taking the lines oe ny one from the file

                            String op = "", shamt = "", func = "", offValue = "";
                            String rs, rt, rd;
                            data = data.replaceAll("(, )|[(]|(: )", " ");         // we are taking the input here and getting rid of the ,  and ()
                            data = data.replaceAll("[)]", "");


                            split = data.split(" ");               // we are assigning input to an string array by dividing according to spaces
                            System.out.println("Instruction: " + data);

                            if (split[0].equals("swap") || split[0].equals("sort") || split[0].equals("for1tst") || split[0].equals("for2tst") || split[0].equals("exit1") || split[0].equals("exit2")){
                                split[0] = split[1];          // if we have any labels we change the structure to our normal format
                                split[1] = split[2];
                                split[2] = split[3];
                                split[3] = split[4];
                            }



                            instructions inst = new instructions();

                            if (split[0].equals("add") || split[0].equals("slt")) {
                                shamt = "00000";
                                op = "000000";
                                if (split[0].equals("add")) {
                                    func = inst.r_type.get(split[0]);
                                } else if (split[0].equals("slt")) {
                                    func = inst.r_type.get(split[0]);
                                }
                                registers r1 = new registers();
                                registers r2 = new registers();
                                registers r3 = new registers();
                                rs = r1.names.get(split[2]);
                                rt = r2.names.get(split[3]);
                                rd = r3.names.get(split[1]);
                                rs = rs.replaceAll("[$]", "");
                                rt = rt.replaceAll("[$]", "");
                                rd = rd.replaceAll("[$]", "");
                                rs = Integer.toBinaryString(Integer.parseInt(rs));
                                rt = Integer.toBinaryString(Integer.parseInt(rt));
                                rd = Integer.toBinaryString(Integer.parseInt(rd));

                                int l = 0;
                                if (rs.length() < 5) {
                                    l = 5 - rs.length();
                                    rs = "0".repeat(l) + rs;
                                }
                                l = 0;
                                if (rd.length() < 5) {
                                    l = 5 - rd.length();                             // we make sure rs, rt, rd are 5 bit everytime
                                    rd = "0".repeat(l) + rd;
                                }
                                l = 0;
                                if (rt.length() < 5) {
                                    l = 5 - rt.length();
                                    rt = "0".repeat(l) + rt;
                                }

                                String add = op + rs + rt + rd + shamt + func;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                                System.out.println("Ox" + hexadd);
                                System.out.println("address" + address);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();


                            } else if (split[0].equals("sll")) {
                                op = "000000";
                                func = inst.r_type.get(split[0]);
                                rs = "00000";
                                registers r2 = new registers();
                                registers r3 = new registers();
                                shamt = Integer.toBinaryString(Integer.parseInt(split[3]));
                                rt = r2.names.get(split[2]);
                                rd = r3.names.get(split[1]);
                                rt = rt.replaceAll("[$]", "");
                                rd = rd.replaceAll("[$]", "");
                                rt = Integer.toBinaryString(Integer.parseInt(rt));
                                rd = Integer.toBinaryString(Integer.parseInt(rd));

                                int l = 0;
                                if (rd.length() < 5) {
                                    l = 5 - rd.length();                             // we make sure rs, rt, rd are 5 bit everytime
                                    rd = "0".repeat(l) + rd;
                                }
                                l = 0;
                                if (rt.length() < 5) {
                                    l = 5 - rt.length();
                                    rt = "0".repeat(l) + rt;
                                }
                                l = 0;
                                if (shamt.length() < 5) {
                                    l = 5 - shamt.length();
                                    shamt = "0".repeat(l) + shamt;
                                }

                                String add = op + rs + rt + rd + shamt + func;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                                System.out.println("Ox" + hexadd);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();

                            }


                            else if (split[0].equals("lw") || split[0].equals("sw")) {
                                if (split[0].equals("lw")) {
                                    op = inst.i_type.get(split[0]);
                                } else if (split[0].equals("sw")) {
                                    op = inst.i_type.get(split[0]);
                                }

                                registers r1 = new registers();
                                registers r2 = new registers();
                                rs = r1.names.get(split[3]);
                                rt = r2.names.get(split[1]);
                                offValue = Integer.toBinaryString(Integer.parseInt(split[2]));
                                rs = rs.replaceAll("[$]", "");
                                rt = rt.replaceAll("[$]", "");
                                rs = Integer.toBinaryString(Integer.parseInt(rs));
                                rt = Integer.toBinaryString(Integer.parseInt(rt));

                                int l = 0;
                                if (rs.length() < 5) {
                                    l = 5 - rs.length();                  // we make sure rs, rt, rd are 5 bit everytime
                                    rs = "0".repeat(l) + rs;
                                }
                                l = 0;
                                if (rt.length() < 5) {
                                    l = 5 - rt.length();
                                    rt = "0".repeat(l) + rt;
                                }
                                l = 0;
                                if (offValue.length() < 16) {
                                    l = 16 - offValue.length();
                                    offValue = "0".repeat(l) + offValue;
                                }

                                String add = op + rs + rt + offValue;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + offValue);
                                System.out.println("Ox" + hexadd);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();

                            } else if (split[0].equals("addi") || split[0].equals("slti")) {
                                if (split[0].equals("addi")) {
                                    op = inst.i_type.get(split[0]);
                                } else if (split[0].equals("slti")) {
                                    op = inst.i_type.get(split[0]);
                                }
                                registers r1 = new registers();
                                registers r2 = new registers();
                                rs = r1.names.get(split[2]);
                                rt = r2.names.get(split[1]);
                                offValue = Integer.toBinaryString(Integer.parseInt(split[3]));
                                rs = rs.replaceAll("[$]", "");
                                rt = rt.replaceAll("[$]", "");
                                rs = Integer.toBinaryString(Integer.parseInt(rs));
                                rt = Integer.toBinaryString(Integer.parseInt(rt));

                                int l = 0;
                                if (rs.length() < 5) {
                                    l = 5 - rs.length();                  // we make sure rs, rt, rd are 5 bit everytime
                                    rs = "0".repeat(l) + rs;
                                }
                                l = 0;
                                if (rt.length() < 5) {
                                    l = 5 - rt.length();
                                    rt = "0".repeat(l) + rt;
                                }
                                l = 0;
                                if (offValue.length() < 16) {
                                    l = 16 - offValue.length();
                                    offValue = "0".repeat(l) + offValue;
                                }
                                if (offValue.length() >16) {
                                    offValue = offValue.substring(16,32);
                                }

                                String add = op + rs + rt + offValue;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + offValue);
                                System.out.println("Ox" + hexadd);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();


                            } else if (split[0].equals("beq") || split[0].equals("bne")) {
                                Long offSet ;
                                if (split[0].equals("beq")) {
                                    op = inst.i_type.get(split[0]);
                                } else if (split[0].equals("bne")) {
                                    op = inst.i_type.get(split[0]);
                                }
                                String label = "";
                                String offsetbinary = "";
                                if (split[3].equals("swap")){
                                    label = swap[0];
                                    if (label.compareTo(address) > 0){
                                        offSet = Long.parseLong(swap[0],16) - Long.parseLong(address,16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = future(offsetbinary);
                                    }else if (label.compareTo(address) < 0){
                                        offSet = Long.parseLong(address,16) - Long.parseLong(swap[0],16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = past(offsetbinary);
                                    }
                                }else if (split[3].equals("sort")) {
                                    label = sort[0];
                                    if (label.compareTo(address) > 0){
                                        offSet = Long.parseLong(sort[0],16) - Long.parseLong(address,16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = future(offsetbinary);
                                    }else if (label.compareTo(address) < 0){
                                        offSet = Long.parseLong(address,16) - Long.parseLong(sort[0],16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = past(offsetbinary);
                                    }
                                }else if (split[3].equals("for1tst")) {
                                    label = for1tst[0];
                                    if (label.compareTo(address) > 0){
                                        offSet = Long.parseLong(for1tst[0],16) - Long.parseLong(address,16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = future(offsetbinary);
                                    }else if (label.compareTo(address) < 0){
                                        offSet = Long.parseLong(address,16) - Long.parseLong(for1tst[0],16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = past(offsetbinary);
                                    }
                                }else if (split[3].equals("for2tst")) {
                                    label = for2tst[0];
                                    if (label.compareTo(address) > 0){
                                        offSet = Long.parseLong(for2tst[0],16) - Long.parseLong(address,16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = future(offsetbinary);
                                    }else if (label.compareTo(address) < 0){
                                        offSet = Long.parseLong(address,16) - Long.parseLong(for2tst[0],16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = past(offsetbinary);
                                    }
                                }else if (split[3].equals("exit1")) {
                                    label = exit1[0];
                                    if (label.compareTo(address) > 0){
                                        offSet = Long.parseLong(exit1[0],16) - Long.parseLong(address,16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = future(offsetbinary);
                                    }else if (label.compareTo(address) < 0){
                                        offSet = Long.parseLong(address,16) - Long.parseLong(exit1[0],16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = past(offsetbinary);
                                    }else {

                                    }
                                }else if (split[3].equals("exit2")) {
                                    label = exit2[0];
                                    if (label.compareTo(address) > 0){
                                        offSet = Long.parseLong(exit2[0],16) - Long.parseLong(address,16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = future(offsetbinary);
                                    }else if (label.compareTo(address) < 0){
                                        offSet = Long.parseLong(address,16) - Long.parseLong(exit2[0],16);
                                        offsetbinary = Long.toBinaryString(offSet);
                                        offsetbinary = past(offsetbinary);
                                    }
                                }
                                registers r1 = new registers();
                                registers r2 = new registers();
                                rs = r1.names.get(split[2]);
                                rt = r2.names.get(split[1]);
                                rs = rs.replaceAll("[$]", "");
                                rt = rt.replaceAll("[$]", "");
                                rs = Integer.toBinaryString(Integer.parseInt(rs));
                                rt = Integer.toBinaryString(Integer.parseInt(rt));




                                int l = 0;
                                if (rs.length() < 5) {
                                    l = 5 - rs.length();                  // we make sure rs, rt, rd are 5 bit everytime
                                    rs = "0".repeat(l) + rs;
                                }
                                l = 0;
                                if (rt.length() < 5) {
                                    l = 5 - rt.length();
                                    rt = "0".repeat(l) + rt;
                                }
                                l = 0;
                                if (offsetbinary.length() < 16) {
                                    l = 16 - offsetbinary.length();
                                    offsetbinary = "0".repeat(l) + offsetbinary;
                                }

                                String add = op + rs + rt + offsetbinary;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + offsetbinary);
                                System.out.println("Ox" + hexadd);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();
                            }
                            else if (split[0].equals("jr")) {
                                shamt = "00000";
                                op = "000000";
                                rt = "00000";
                                rd = "00000";
                                func = inst.r_type.get(split[0]);
                                registers r1 = new registers();
                                rs = r1.names.get(split[1]);
                                rs = rs.replaceAll("[$]", "");
                                rs = Integer.toBinaryString(Integer.parseInt(rs));

                                int l = 0;
                                if (rs.length() < 5) {
                                    l = 5 - rs.length();                 // we make sure rs, rt, rd are 5 bit everytime
                                    rs = "0".repeat(l) + rs;
                                }

                                String add = op + rs + rt + rd + shamt + func;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                                System.out.println("Ox" + hexadd);
                                System.out.println("address" + address);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();
                            }
                            else if (split[0].equals("j")) {
                                Long offSet ;
                                op = inst.j_type.get(split[0]);
                                registers r1 = new registers();
                                String label = "";

                                char[] arr = new char[32];
                                String offsetbinary = "";
                                if (split[1].equals("swap")){
                                    label = swap[0];
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("sort")) {
                                    label = sort[0];
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("for1tst")) {
                                    label = for1tst[0];
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("for2tst")) {
                                    label = for2tst[0];
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("exit1")) {
                                    label = exit1[0];
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("exit2")) {
                                    label = exit2[0];
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }

                                int l = 0;
                                if (offsetbinary.length() < 26) {
                                    l = 26 - offsetbinary.length();
                                    offsetbinary = "0".repeat(l) + offsetbinary;
                                }

                                String add = op + offsetbinary;


                                String hexadd = String.format("%8X", Long.parseLong(add,2)).replace(' ', '0');


                                System.out.println(op + "/" + offsetbinary);
                                System.out.println("Ox" + hexadd);
                                System.out.println("address" + address);
                                hexadd = "0x" + hexadd;
                                fout.write(hexadd);
                                fout.flush();
                            }
                            else if (split[0].equals("move")) {
                                shamt = "00000";
                                op = "000000";
                                rt = "00000";
                                func = inst.r_type.get(split[0]);            //move $1, $2 translates to add $1, $2, $0
                                registers r1 = new registers();
                                registers r2 = new registers();
                                rd = r1.names.get(split[1]);
                                rs = r2.names.get(split[2]);
                                rd = rd.replaceAll("[$]", "");
                                rs = rs.replaceAll("[$]", "");
                                rd = Integer.toBinaryString(Integer.parseInt(rd));
                                rs = Integer.toBinaryString(Integer.parseInt(rs));

                                int l = 0;
                                if (rs.length() < 5) {
                                    l = 5 - rs.length();
                                    rs = "0".repeat(l) + rs;
                                }
                                l = 0;
                                if (rd.length() < 5) {          // we make sure rs, rt, rd are 5 bit everytime
                                    l = 5 - rd.length();
                                    rd = "0".repeat(l) + rd;
                                }

                                String add = op + rs + rt + rd + shamt + func;

                                String hexadd = String.format("%8X", Long.parseLong(add, 2)).replace(' ', '0');


                                System.out.println(op + "/" + rs + "/" + rt + "/" + rd + "/" + shamt + "/" + func);
                                System.out.println("Ox" + hexadd);
                                hexadd = "0x" + hexadd;
                                fout.write(hexadd);
                                fout.flush();
                            }
                            else if (split[0].equals("jal")) {
                                op = inst.j_type.get(split[0]);
                                registers r1 = new registers();
                                String label = "";

                                char[] arr = new char[32];
                                String offsetbinary = "";
                                Long value = Long.parseLong(address_hold, 16);            // we are changing the address by adding +4 to hexa form to hold the next instruction for the jal
                                value+=4;
                                String next_address = Long.toHexString(value);
                                if (split[1].equals("swap")){
                                    label = next_address;
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("sort")) {
                                    label = next_address;
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("for1tst")) {
                                    label = next_address;
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("for2tst")) {
                                    label = next_address;
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("exit1")) {
                                    label = next_address;
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }else if (split[1].equals("exit2")) {
                                    label = next_address;
                                    label =  Long.toBinaryString(Long.parseLong(label,16));
                                    offsetbinary = label.substring(4,30);
                                }

                                int l = 0;
                                if (offsetbinary.length() < 26) {
                                    l = 26 - offsetbinary.length();
                                    offsetbinary = "0".repeat(l) + offsetbinary;
                                }

                                String add = op + offsetbinary;


                                String hexadd = String.format("%8X", Long.parseLong(add,2)).replace(' ', '0');


                                System.out.println(op + "/" + offsetbinary);
                                System.out.println("Ox" + hexadd);
                                System.out.println("address" + address);
                                hexadd = "0x" + hexadd;

                                fout.write(hexadd);
                                fout.flush();
                            }

                            Long value = Long.parseLong(address_hold, 16);            // we are changing the address by adding +4 to hexa form
                            value+=4;
                            address_hold = Long.toHexString(value);
                        }
                        fout.close();
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("A problem occured while loading the file");
                        e.printStackTrace();
                    }

                    break;
                }
                case -1:{
                    System.out.println("Goodbye");
                    break;
                }
                default : {
                    System.out.println("Please enter a correct number!!");
                }
            }
        }while(choose != -1);
    }

    

    public static String past(String offset) {             // if the target address < address we substract address - target address then 2's complement then << 2
        int i;
        char[] arr;
        arr = offset.toCharArray();                      // 1's complement
        for (i = 0; i < arr.length; i++){
            if (arr[i] == '0') {
                arr[i] = '1';
            } else{
                arr[i] = '0';
            }
        }
        String s = new String(arr);
        offset = s;


        int intOfSet = Integer.parseInt(offset,2);
        intOfSet++;                                          //   +1 to 1's complemet to make it 2's complement  (our addres should be negative)
        offset = Integer.toBinaryString(intOfSet>>2);     //   /4

        return offset;
    }

    public static String twoscomp(String offValue){                  // twos complemet
        int i;
        char[] arr;
        arr = offValue.toCharArray();                      // 1's complement
        for (i = 0; i < arr.length; i++){
            if (arr[i] == '0') {
                arr[i] = '1';
            } else{
                arr[i] = '0';
            }
        }
        System.out.println("i"+i);
        String s = new String(arr);
        offValue = s;
        int intOfSet = Integer.parseInt(offValue,2);
        intOfSet++;                                          //+1 to make it twos complement
        offValue = Integer.toBinaryString(intOfSet);
        return offValue;
    }

    public static String future(String offset) {                 // we divide the address by 4
        int intOfSet = Integer.parseInt(offset,2);            // /4
        offset = Integer.toBinaryString(intOfSet>>2);

        return offset;
    }
}
