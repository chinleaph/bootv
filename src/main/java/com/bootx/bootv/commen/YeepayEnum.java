package com.bootx.bootv.commen;

public interface YeepayEnum {
    public enum IdentityType{
        MAC("网卡地址"), IMEI("国际移动设备标识"), ID_CARD("用户身份证号"), PHONE("手机号"), EMAIL("邮箱"), USER_ID("用户 id"), AGREEMENT_NO("用户纸质订单协议号");
        private String name;

        private IdentityType(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum IdCardType{
        ID("身份证");
        private String name;
        private IdCardType(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
