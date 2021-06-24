package liu.hope.study.matcher;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {
    public static void main(String[] args) {
        String userCommand = "#SBATCH -o job.%j.out\n" +
                "#SBATCH --ntasks=12\n" +
                "#SBATCH --qos=low\n" +
                "#SBATCH -c 150\n" +
                "#SBATCH --nodes=1\n" +
                "#SBATCH --ntasks-per-node=1";
        String[] userCommands = userCommand.split("\n");
        // CLI模式,用户自己写执行命令,对命令进行解析,获取用户的总进程数和每进程使用的cpu核数   SBATCH -n 1 -c 1
        // -N, --nodes=<minnodes[-maxnodes]>    -n, --ntasks=<number>    -c, --cpus-per-task=<ncpus>
        Map<String, Integer> commandMap = Maps.newHashMapWithExpectedSize(3);
        commandMap.put("-N[ ]+[\\d]+|--nodes=[\\d]+",null);
        commandMap.put("-n[ ]+[\\d]+|--ntasks=+[\\d]+",null);
        commandMap.put("-c[ ]+[\\d]+|--cpus-per-task=[\\d]+",null);
        Pattern patternNum = Pattern.compile("[\\d]+");
        Arrays.stream(userCommands).filter(u -> u.contains("#SBATCH")).forEach(u -> {
            commandMap.keySet().forEach(k -> {
                Arrays.stream(k.split("[|]")).forEach(p -> {
                    Pattern pattern = Pattern.compile(k);
                    Matcher matcher = pattern.matcher(u);
                    if (matcher.find()) {
                        Matcher matcher1 = patternNum.matcher(matcher.group());
                        if (matcher1.find()) {
                            commandMap.put(k, Integer.valueOf(matcher1.group()));
                        }
                    }
                });
            });
        });
        // -n: 任务数
        int taskNum = Optional.ofNullable(commandMap.get("-n[ ]+[\\d]+|--ntasks=+[\\d]+")).orElse(-1);
        // -N: 节点数
        int nodeNum = Optional.ofNullable(commandMap.get("-N[ ]+[\\d]+|--nodes=[\\d]+")).orElse(-1);
        // -c: 每任务核数
        int openmpThreads = Optional.ofNullable(commandMap.get("-c[ ]+[\\d]+|--cpus-per-task=[\\d]+")).orElse(-1);

        int maxProcessPerNode = (int) Math.ceil((double) taskNum / nodeNum);
        int cpuPerTask = 8 / maxProcessPerNode;
        System.out.println(maxProcessPerNode);
        System.out.println(cpuPerTask);
    }
}
