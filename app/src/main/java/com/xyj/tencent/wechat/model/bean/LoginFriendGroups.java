package com.xyj.tencent.wechat.model.bean;

import com.xyj.tencent.wechat.ui.widget.Indexable;

import java.util.List;

public class LoginFriendGroups {
    /**
     * msg : 查询成功
     * code : 200
     * success : true
     * result : [{"headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/ibfurMLEX1oVL41ib0CdN7av2x5BwbuGkWiaG4Pf31Xa1piawH82gCo4oxTeLHvxFngx0WBxuSDPQx5PiaTGwb5L4XY3YMWvEzA0dOiaTAU1R1l90/96","wxno":"ningjing4343","wordsIntercept":"你妹|滚蛋|服了|傻瓜","nickname":"宁静","wechatId":"054c6eda53784528af3e20b992319a98","groups":[{"groupsId":"ecc5c19f2f9741b7a93cdbea46719114","groupsName":"新加好友","friends":[{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"wxid_7leqp87di2qm22","wxno":"hepburn-design","nickname":"V-Audrey","remarkname":"Audrey","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/MTJcSpy0eCzEd9ibRolw0WcALN7XvajFTibNQ1riboZDxKwIWp1iaNj0fEsYSUkb2eADxbkxllz4xm43zht8rC2Run3p36QoSwjibOYREIuc9LO0/0","phone":"","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"2","addFrom":"30","id":"713e009cd46c456cad282b4241c023bd"},{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"wxid_kwd8tbhbbgsr22","wxno":"a54414512","nickname":"寄你笑颜","remarkname":"小猫猫","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/goNz10QyiaYvqOAthe5G6K3D5BQ2VO5Vr4vTszNibWr0gukGIdic7BnNfZJbyeDB0QaickWdDgLGwTIRjz572NoeXNwsCicS4bGR41IpicC1mQsIQ/0","phone":"-","type":"0","isRead":true,"city":"","sex":"1","addFrom":"3","id":"9841501a428a4b80b402a63beadf4443"},{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"zsp343757500","wxno":"zsp343757500","nickname":"木鱼","remarkname":"少鹏","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/A0t80Emg1V97S5iaBOqRziaIkeCnklpSJ55BFZV58qB8VrHAbtFEwzCExmctpy25yzOwYTPbiajTRT13ElHj1UTe1Bwm6d4pe5wODUS7LMtq5w/0","phone":"18819447160","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"1","addFrom":"3","id":"e02658a7a29c4f2ea448ddbffe92e9c3"}],"newFriends":0}],"newFriends":0,"wordsNotice":"混蛋1|滚|去你妈的|gg","status":"1"},{"headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/who3lVI3zRsDmaNBgZhdyKlzDcfTmDOicRIOEKOhB78ticwrrd1Tluc1h06ZNyTRiaxp4SMUnwKpNGC1F83XJoWq358aQ8ZHPKv8RI5aFJLPuU/96","wxno":"hsl_test","wordsIntercept":"你妹|滚蛋|服了|傻瓜","nickname":"NA","wechatId":"0","groups":[{"groupsId":"ecc5c19f2f9741b7a93cdbea46719114","groupsName":"新加好友","friends":[{"wechatId":"0","wxid":"zsp343757500","wxno":"zsp343757500","nickname":"木鱼","remarkname":"","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/PhFSRZj2DlrOd1LDialCXlltnGYYINhuC9SOcv08QVZfNQJ5tllGDWrc2v2x782ICj7uFur1XT1Y0IWzudnIqn7IXcODia3FL9wQtKe2evuVI/0","phone":"18819447160,","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"1","addFrom":"3","id":"8cd684533a9a4fc6bc22da7f719ada74"}],"newFriends":0}],"newFriends":0,"wordsNotice":"混蛋1|滚|去你妈的|gg","status":"1"}]
     */

    private String msg;
    private String code;
    private boolean success;
    private List<ResultBean> result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * headImgUrl : http://wx.qlogo.cn/mmhead/ver_1/ibfurMLEX1oVL41ib0CdN7av2x5BwbuGkWiaG4Pf31Xa1piawH82gCo4oxTeLHvxFngx0WBxuSDPQx5PiaTGwb5L4XY3YMWvEzA0dOiaTAU1R1l90/96
         * wxno : ningjing4343
         * wordsIntercept : 你妹|滚蛋|服了|傻瓜
         * nickname : 宁静
         * wechatId : 054c6eda53784528af3e20b992319a98
         * groups : [{"groupsId":"ecc5c19f2f9741b7a93cdbea46719114","groupsName":"新加好友","friends":[{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"wxid_7leqp87di2qm22","wxno":"hepburn-design","nickname":"V-Audrey","remarkname":"Audrey","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/MTJcSpy0eCzEd9ibRolw0WcALN7XvajFTibNQ1riboZDxKwIWp1iaNj0fEsYSUkb2eADxbkxllz4xm43zht8rC2Run3p36QoSwjibOYREIuc9LO0/0","phone":"","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"2","addFrom":"30","id":"713e009cd46c456cad282b4241c023bd"},{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"wxid_kwd8tbhbbgsr22","wxno":"a54414512","nickname":"寄你笑颜","remarkname":"小猫猫","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/goNz10QyiaYvqOAthe5G6K3D5BQ2VO5Vr4vTszNibWr0gukGIdic7BnNfZJbyeDB0QaickWdDgLGwTIRjz572NoeXNwsCicS4bGR41IpicC1mQsIQ/0","phone":"-","type":"0","isRead":true,"city":"","sex":"1","addFrom":"3","id":"9841501a428a4b80b402a63beadf4443"},{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"zsp343757500","wxno":"zsp343757500","nickname":"木鱼","remarkname":"少鹏","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/A0t80Emg1V97S5iaBOqRziaIkeCnklpSJ55BFZV58qB8VrHAbtFEwzCExmctpy25yzOwYTPbiajTRT13ElHj1UTe1Bwm6d4pe5wODUS7LMtq5w/0","phone":"18819447160","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"1","addFrom":"3","id":"e02658a7a29c4f2ea448ddbffe92e9c3"}],"newFriends":0}]
         * newFriends : 0
         * wordsNotice : 混蛋1|滚|去你妈的|gg
         * status : 1
         */

        private String headImgUrl;
        private String wxno;
        private String wordsIntercept;
        private String nickname;
        private String wechatId;
        private int newFriends;
        private String wordsNotice;
        private String status;
        private List<GroupsBean> groups;

        public String getHeadImgUrl() {
            return headImgUrl;
        }

        public void setHeadImgUrl(String headImgUrl) {
            this.headImgUrl = headImgUrl;
        }

        public String getWxno() {
            return wxno;
        }

        public void setWxno(String wxno) {
            this.wxno = wxno;
        }

        public String getWordsIntercept() {
            return wordsIntercept;
        }

        public void setWordsIntercept(String wordsIntercept) {
            this.wordsIntercept = wordsIntercept;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getWechatId() {
            return wechatId;
        }

        public void setWechatId(String wechatId) {
            this.wechatId = wechatId;
        }

        public int getNewFriends() {
            return newFriends;
        }

        public void setNewFriends(int newFriends) {
            this.newFriends = newFriends;
        }

        public String getWordsNotice() {
            return wordsNotice;
        }

        public void setWordsNotice(String wordsNotice) {
            this.wordsNotice = wordsNotice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<GroupsBean> getGroups() {
            return groups;
        }

        public void setGroups(List<GroupsBean> groups) {
            this.groups = groups;
        }

        public static class GroupsBean {
            /**
             * groupsId : ecc5c19f2f9741b7a93cdbea46719114
             * groupsName : 新加好友
             * friends : [{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"wxid_7leqp87di2qm22","wxno":"hepburn-design","nickname":"V-Audrey","remarkname":"Audrey","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/MTJcSpy0eCzEd9ibRolw0WcALN7XvajFTibNQ1riboZDxKwIWp1iaNj0fEsYSUkb2eADxbkxllz4xm43zht8rC2Run3p36QoSwjibOYREIuc9LO0/0","phone":"","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"2","addFrom":"30","id":"713e009cd46c456cad282b4241c023bd"},{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"wxid_kwd8tbhbbgsr22","wxno":"a54414512","nickname":"寄你笑颜","remarkname":"小猫猫","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/goNz10QyiaYvqOAthe5G6K3D5BQ2VO5Vr4vTszNibWr0gukGIdic7BnNfZJbyeDB0QaickWdDgLGwTIRjz572NoeXNwsCicS4bGR41IpicC1mQsIQ/0","phone":"-","type":"0","isRead":true,"city":"","sex":"1","addFrom":"3","id":"9841501a428a4b80b402a63beadf4443"},{"wechatId":"054c6eda53784528af3e20b992319a98","wxid":"zsp343757500","wxno":"zsp343757500","nickname":"木鱼","remarkname":"少鹏","headImgUrl":"http://wx.qlogo.cn/mmhead/ver_1/A0t80Emg1V97S5iaBOqRziaIkeCnklpSJ55BFZV58qB8VrHAbtFEwzCExmctpy25yzOwYTPbiajTRT13ElHj1UTe1Bwm6d4pe5wODUS7LMtq5w/0","phone":"18819447160","type":"0","isRead":true,"region":"广东广州","province":"广东","city":"广州","sex":"1","addFrom":"3","id":"e02658a7a29c4f2ea448ddbffe92e9c3"}]
             * newFriends : 0
             */

            private String groupsId;
            private String groupsName;
            private int newFriends;
            private List<FriendsBean> friends;

            public String getGroupsId() {
                return groupsId;
            }

            public void setGroupsId(String groupsId) {
                this.groupsId = groupsId;
            }

            public String getGroupsName() {
                return groupsName;
            }

            public void setGroupsName(String groupsName) {
                this.groupsName = groupsName;
            }

            public int getNewFriends() {
                return newFriends;
            }

            public void setNewFriends(int newFriends) {
                this.newFriends = newFriends;
            }

            public List<FriendsBean> getFriends() {
                return friends;
            }

            public void setFriends(List<FriendsBean> friends) {
                this.friends = friends;
            }

            public static class FriendsBean implements Indexable {
                /**
                 * wechatId : 054c6eda53784528af3e20b992319a98
                 * wxid : wxid_7leqp87di2qm22
                 * wxno : hepburn-design
                 * nickname : V-Audrey
                 * remarkname : Audrey
                 * headImgUrl : http://wx.qlogo.cn/mmhead/ver_1/MTJcSpy0eCzEd9ibRolw0WcALN7XvajFTibNQ1riboZDxKwIWp1iaNj0fEsYSUkb2eADxbkxllz4xm43zht8rC2Run3p36QoSwjibOYREIuc9LO0/0
                 * phone :
                 * type : 0
                 * isRead : true
                 * region : 广东广州
                 * province : 广东
                 * city : 广州
                 * sex : 2
                 * addFrom : 30
                 * id : 713e009cd46c456cad282b4241c023bd
                 */

                private String wechatId;
                private String wxid;
                private String wxno;
                private String nickname;
                private String remarkname;
                private String headImgUrl;
                private String phone;
                private String type;
                private boolean isRead;
                private String region;
                private String province;
                private String city;
                private String sex;
                private String addFrom;
                private String id;

                public String getWechatId() {
                    return wechatId;
                }

                public void setWechatId(String wechatId) {
                    this.wechatId = wechatId;
                }

                public String getWxid() {
                    return wxid;
                }

                public void setWxid(String wxid) {
                    this.wxid = wxid;
                }

                public String getWxno() {
                    return wxno;
                }

                public void setWxno(String wxno) {
                    this.wxno = wxno;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getRemarkname() {
                    return remarkname;
                }

                public void setRemarkname(String remarkname) {
                    this.remarkname = remarkname;
                }

                public String getHeadImgUrl() {
                    return headImgUrl;
                }

                public void setHeadImgUrl(String headImgUrl) {
                    this.headImgUrl = headImgUrl;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public boolean isIsRead() {
                    return isRead;
                }

                public void setIsRead(boolean isRead) {
                    this.isRead = isRead;
                }

                public String getRegion() {
                    return region;
                }

                public void setRegion(String region) {
                    this.region = region;
                }

                public String getProvince() {
                    return province;
                }

                public void setProvince(String province) {
                    this.province = province;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getAddFrom() {
                    return addFrom;
                }

                public void setAddFrom(String addFrom) {
                    this.addFrom = addFrom;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                private String  sortLetters;

                public void setSortLetters(String sortLetters) {
                    this.sortLetters = sortLetters;
                }

                public String getSortLetters() {
                    return sortLetters;
                }

                @Override
                public String getIndex() {
                    return sortLetters;
                }
            }
        }
    }
}
