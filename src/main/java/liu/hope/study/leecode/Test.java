package liu.hope.study.leecode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String josn = "{\"totp\":\"\",\"privatekey\":\"\",\"hostname\":\"182.92.194.107\",\"password\":\"8EaA5bX41aU1O1b\",\"port\":\"\",\"passphrase\":\"\",\"term\":\"xterm-256color\",\"_xsrf\":\"2|7752bf52|66496787f2d21633d35c5cb3c75cf8ce|1624247423\",\"username\":\"cloudam\"}";
        System.out.println(new String(josn.getBytes("UTF-8")));
        String t = "AVERDXIVYERDIAM";
        String p = "RDXI";
        System.out.println(getIndex(t, p));
        System.out.println(false || true);
//        String CLOUDAM_INPUT_PREFIX = "$EXAMPLES";
//        String CLOUDAM_INPUT_DIR = "/public/software/.local/easybuild/.examples";
//        System.out.println(Arrays.stream("/home/cloudam/.Share/initial_copy.rst,/home/cloudam/input/.Share/initial_copy.rst,$EXAMPLES/initial_copy.rst".split(","))
//                .filter(path -> path.startsWith(CLOUDAM_INPUT_PREFIX) || (!path.startsWith(CLOUDAM_INPUT_PREFIX) && path.contains("/input") && !path.contains("/.Share")))
//                .map(path -> path.replace(CLOUDAM_INPUT_PREFIX, CLOUDAM_INPUT_DIR))
//                .collect(Collectors.joining(" ")));
//
//        Map getmap = getmap("/home/cloudam/.Share/initial_copy.rst,/home/cloudam/input/.Share/initial_copy.rst,$EXAMPLES/initial_copy.rst");
//        System.out.println(getmap);

        BiFunction<Double, Double, String> numToRate = (num1, num2) -> 0 == num2.intValue() ? "0.00%" : String.format("%.2f", ((num1 / num2) * 100)) + "%";
        System.out.println(numToRate.apply(0.0,0.0));

        List<String> stringList = new ArrayList<>();
        stringList.add("111");

        Lists.newArrayList("extreme", "cpfs", stringList).forEach(System.out::println);

        System.out.println("------------------");


        String script = "if [ `mount -l | grep '/mnt/nas' | wc -l` -gt 0 ]; then umount -fl /mnt/nas; fi; mount -l |grep '/mnt/nas' | wc -l";
        script = String.format(script, "1222", "1122");
        System.out.println(script);


        String ss = "#aa# is #aa#, not #bb#";
        String lixiang = ss.replaceAll("#aa#", "lixiang");
        System.out.println(lixiang);
        List<String> lll = Lists.newArrayList("aaa", "bbb", "ccc");
        JSONArray.parseArray(JSON.toJSONString(lll), String.class).forEach(System.out::println);

        String version = "v10";
        System.out.println("v" + (Integer.parseInt(version.substring(1)) + 1));


        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("3", "1");
        map.put("4", "1");
        map.put("5", "1");

        map.remove("1");
        System.out.println(map);


        System.out.println(getMountTargetDomain("mkdir -p /mnt/nas;chmod 775 /mnt/nas;chown cloudam: /mnt/nas;sudo mount -t nfs -o vers=3,nolock,proto=tcp,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 013afedc-400t.cn-beijing.extreme.nas.aliyuncs.com:/ /mnt/nas;"));

    }

    private static String getMountTargetDomain(String fileSystemMountTargetScript) {
        // mkdir -p /mnt/nas;chmod 775 /mnt/nas;chown cloudam: /mnt/nas;sudo mount -t nfs -o vers=3,nolock,proto=tcp,rsize=1048576,wsize=1048576,hard,timeo=600,retrans=2,noresvport 013afedc-400t.cn-beijing.extreme.nas.aliyuncs.com:/ /mnt/nas;
        String[] strArrays = StringUtils.split(fileSystemMountTargetScript, " ");
        return Arrays.stream(strArrays).filter(str -> str.contains("extreme.nas.aliyuncs.com"))
                .map(str -> StringUtils.split(str, ":")[0])
                .findFirst()
                .orElse(null);
    }

    private static int getIndex(String t, String p) {
        if (!t.contains(p)) {
            return 0;
        }
        char[] tChars = t.toCharArray();
        char[] pChars = p.toCharArray();
        if (tChars.length > pChars.length) {
            return t.indexOf(p) + 1;
        } else {
            return p.indexOf(t) + 1;
        }
    }

    public static Map getmap(String inputfile) {
        String CLOUDAM_INPUT_PREFIX = "$EXAMPLES";
        String CLOUDAM_INPUT_DIR = "/public/software/.local/easybuild/.examples";
        Map<String, String> map = new HashMap<>();

        String moveInputsTarget = Arrays.stream(inputfile.split(","))
                .filter(path -> !path.startsWith(CLOUDAM_INPUT_PREFIX) && !path.contains("/input") && !path.contains("/.Share"))
                .collect(Collectors.joining(" "));

        String copyInputsTarget = Arrays.stream(inputfile.split(","))
                .filter(path -> path.startsWith(CLOUDAM_INPUT_PREFIX) || (!path.startsWith(CLOUDAM_INPUT_PREFIX) && path.contains("/input") && !path.contains("/.Share")))
                .map(path -> path.replace(CLOUDAM_INPUT_PREFIX, CLOUDAM_INPUT_DIR))
                .collect(Collectors.joining(" "));

        map.put("moveInputsCommand", StringUtils.isNotBlank(moveInputsTarget)
                && StringUtils.isNotBlank(inputfile)?
                "mv -t #INPUT_DIR# " + moveInputsTarget + "; chown -R cloudam: #INPUT_DIR#; chmod -R 775 #INPUT_DIR#; " : "");

        map.put("copyInputsCommand", StringUtils.isNotBlank(copyInputsTarget)
                && StringUtils.isNotBlank(inputfile)?
                "cp -t #INPUT_DIR# " + copyInputsTarget + "; chown -R cloudam: #INPUT_DIR#; chmod -R 775 #INPUT_DIR#; " : "");
        return map;
    }
}
