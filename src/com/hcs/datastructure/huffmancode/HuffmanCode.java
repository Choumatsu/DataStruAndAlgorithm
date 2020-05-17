package com.hcs.datastructure.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {

        //测试压缩文件
        /*String srcFile = "F:/壁纸/7c709885e2734c8628acccb5dac753e5.jpg";
        String dstFile = "F:/壁纸/dst.zip";

        zipFile(srcFile, dstFile);*/

        //测试解压文件
        String zipFile = "F:/壁纸/dst.zip";
        String dst = "F:/壁纸/dst.jpg";
        unZipFile(zipFile,dst);


        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("原始长度：" + contentBytes.length);

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("huffmanCodeBytes = " + Arrays.toString(huffmanCodeBytes));
        System.out.println("压缩后长度：" + huffmanCodeBytes.length);
        System.out.println("压缩比：" + ((float) (contentBytes.length - huffmanCodeBytes.length) / (float) content.length()));

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println("原字符串:" + new String(sourceBytes));*/

        /*List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        System.out.println("哈夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        preOrder(huffmanTreeRoot);

        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        System.out.println("生成的哈夫曼编码表:" + huffmanCodes);

        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes = "+Arrays.toString(huffmanCodeBytes));*/
    }

    //解压

    /**
     * @param zipFile 准备解压的文件路径
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {

        //定义输入流
        InputStream is = null;
        //定义对象输入流
        ObjectInputStream ois = null;
        //定义文件输出流
        OutputStream os = null;

        try {
            is = new FileInputStream(zipFile);

            ois = new ObjectInputStream(is);

            byte[] huffmanBytes = (byte[])ois.readObject();

            Map<Byte,String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            //写入到目标文件
            os = new FileOutputStream(dstFile);

            //写数据
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //压缩文件

    /**
     * @param srcFile 传入的需要压缩的文件全路径
     * @param dstFile 压缩后保存的路径
     */
    public static void zipFile(String srcFile, String dstFile) {

        //创建文件输入流
        FileInputStream is = null;
        //创建文件输出流
        ObjectOutputStream oos = null;
        FileOutputStream os = null;
        try {
            is = new FileInputStream(srcFile);
            //创建byte数组
            byte[] b = new byte[is.available()];
            //读取文件
            is.read(b);

            //获取对应的哈弗曼编码表,对源文件压缩
            byte[] huffmanBytes = huffmanZip(b);

            os = new FileOutputStream(dstFile);

            //创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(os);
            //把编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //以对象流的方式写入哈弗曼编码，为了以后恢复文件使用
            //注意一定将哈夫曼编码写入压缩文件
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /***********************************************************/
    //解压

    /**
     * //解码
     *
     * @param huffmanCodes 哈夫曼编码表
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        //字符串按照指定的哈夫曼编码进行解码
        //反转
        Map<String, Byte> map = new HashMap<>();

        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建集合。存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                if (i+count>stringBuilder.length()) {
                    String key =stringBuilder.substring(i);
                    break;
                }
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }

        //把list中数据放入byte[]中
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte转为二进制字符串
     *
     * @param b    传入的byte
     * @param flag 标识是否需要补高位，true表示需要,如果是最后一个字节，无需补高位
     * @return b对应的二进制字符串（补码返回）
     */
    private static String byteToBitString(boolean flag, byte b) {

        int temp = b;

        if (flag) {//正数补高位

            temp |= 256;//按位或 256 -> 1 0000 0000
        }


        String str = Integer.toBinaryString(temp);

        if (flag) {
            return str.substring(str.length() - 8);

        } else {
            return str;
        }

    }


    /**************************************************************************************/
    //压缩

    /**
     * 封装
     *
     * @param bytes 原始字符串对应的字节数组
     * @return 经过哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成对应的哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //压缩得到压缩后的哈夫曼编码字节数组
        return zip(bytes, huffmanCodes);
    }

    //将字符创对应的byte[]，通过编码表，生成编码过后的byte[]

    /**
     * @param bytes        原始的字符串对应的byte[]
     * @param huffmanCodes 生成哈夫曼编码map
     * @return 返回编码后的byte[]
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将对应的字符串转成byte[]
        int len;
        //int len = (stringBuilder+7)/8;//简略写法
        //统计返回byte[] huffmanCodeBytes长度
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //创建存储研所后的byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {//每八位对应一个byte
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    //生成对应的哈夫曼编码
    //编码以map形式存放
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //生成编码表时，需要拼接路径，StringBuilder存储摸个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子结点的哈夫曼编码得到，并放入到huffmanCodes
     *
     * @param node          传入节点
     * @param code          路径：左子节点是0，右子节点为1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        stringBuilder2.append(code);
        if (node != null) {
            //判断是否为非叶子节点
            if (node.data == null) {//非叶子节点
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);

            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }

    }

    //前序遍历
    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空，不能遍历");
        }
    }

    private static List<Node> getNodes(byte[] bytes) {

        ArrayList<Node> nodes = new ArrayList<>();

        Map<Byte, Integer> counts = new HashMap<>();

        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }


        //将每一个键值对转换成一个Node，并加入nodes集合
        //遍历map
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }

    //通过List 创建对应的哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {

            Collections.sort(nodes);

            Node leftNode = nodes.get(0);

            Node rightNode = nodes.get(1);

            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {
    Byte data;
    int weight;//权值，字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        return this.weight - node.weight;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}