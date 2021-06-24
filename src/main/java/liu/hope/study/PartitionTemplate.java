package liu.hope.study;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import java.time.Instant;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author JMW
 * @date 2021/3/18 18:25
 * Description
 */
public class PartitionTemplate {

    @Id
    private String id;

    /**
     * 队列
     */
    private String partition;

    /**
     * 核数
     */
    private int coreNum;

    /**
     * 内存
     */
    private double memory;

    /**
     * 卡类型
     */
    private String gpuName;

    /**
     * 卡数
     */
    private int gpuNum;

    /**
     * 实例类型
     */
    private List<String> instanceTypes;

    @Transient
    private boolean enable;

    private long createdAt = Instant.now().toEpochMilli();

    private long updatedAt = Instant.now().toEpochMilli();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPartition() {
        return partition;
    }

    public void setPartition(String partition) {
        this.partition = partition;
    }

    public int getCoreNum() {
        return coreNum;
    }

    public void setCoreNum(int coreNum) {
        this.coreNum = coreNum;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    public String getGpuName() {
        return gpuName;
    }

    public void setGpuName(String gpuName) {
        this.gpuName = gpuName;
    }

    public int getGpuNum() {
        return gpuNum;
    }

    public void setGpuNum(int gpuNum) {
        this.gpuNum = gpuNum;
    }

    public List<String> getInstanceTypes() {
        return instanceTypes;
    }

    public void setInstanceTypes(List<String> instanceTypes) {
        this.instanceTypes = instanceTypes;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    /**
     * 集群名称
     *
     * @return 集群名称
     */
    public String nameCluster() {
        return StringUtils.startsWith(this.getPartition(), "CPFS") ? this.getPartition() : StringUtils.isNotEmpty(gpuName) ? getGpuName(gpuName, gpuNum) : getCpuName(coreNum, memory);
    }

    private String getGpuName(String gpuName, int gpuNum) {
        return "CE-" + new StringJoiner("-").add(gpuName).add(judgeZone(gpuNum)).toString();
    }

    private String getCpuName(int cpuNum, double memory) {
        return (cpuNum == 2 && memory == 4 ? "DE-" : "CE-") +
                new StringJoiner("-").add(judgeZone(cpuNum)).add(judgeZone(new Double(memory).intValue())).toString();
    }

    private String judgeZone(int num){
        return num > 0 ? "" + num : "*";
    }

    public static void main(String[] args) {
        PartitionTemplate partitionTemplate = new PartitionTemplate();
        partitionTemplate.setPartition("g-t4-2");
        partitionTemplate.setCoreNum(48);
        partitionTemplate.setMemory(186);
        partitionTemplate.setGpuName("NVIDIA® Tesla® T4");
        partitionTemplate.setGpuNum(2);
        System.out.println(partitionTemplate.nameCluster());
        System.out.println("CE-28-48-NVIDIA® Tesla® M40-2");
    }
}
