/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : chess_website

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 09/06/2024 16:45:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `adminname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'lzlz', '888taotao');
INSERT INTO `admin` VALUES (2, 'admin', '123456');
INSERT INTO `admin` VALUES (7, 'lzlzlz', '888taotao');
INSERT INTO `admin` VALUES (11, 'hhhh', 'jsdfj44');
INSERT INTO `admin` VALUES (12, 'llllll', '11111ddd');

-- ----------------------------
-- Table structure for admingrade
-- ----------------------------
DROP TABLE IF EXISTS `admingrade`;
CREATE TABLE `admingrade`  (
  `admingrade_id` int NOT NULL AUTO_INCREMENT,
  `admin_id` int NULL DEFAULT NULL,
  `grade` int NULL DEFAULT NULL COMMENT '1为超级管理员，0为普通管理员',
  PRIMARY KEY (`admingrade_id`) USING BTREE,
  INDEX `admin_id`(`admin_id`) USING BTREE,
  CONSTRAINT `admingrade_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admingrade
-- ----------------------------
INSERT INTO `admingrade` VALUES (1, 1, 1);
INSERT INTO `admingrade` VALUES (2, 2, 0);
INSERT INTO `admingrade` VALUES (5, 7, 1);
INSERT INTO `admingrade` VALUES (9, 11, 0);
INSERT INTO `admingrade` VALUES (10, 12, 0);

-- ----------------------------
-- Table structure for informations
-- ----------------------------
DROP TABLE IF EXISTS `informations`;
CREATE TABLE `informations`  (
  `informations_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`informations_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `informations_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of informations
-- ----------------------------
INSERT INTO `informations` VALUES (1, 1, 'linzhou', '123456', '男', 'lz666lz', '18958750073', '1398696334@qq.com');
INSERT INTO `informations` VALUES (2, 2, 'lz', '123456', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (3, 3, 'lz666', '123456', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (4, 4, 'sb', '654321', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (5, 5, 'qiujiale', '123456', '男', NULL, NULL, NULL);
INSERT INTO `informations` VALUES (6, 6, 'son', '123', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (7, 9, '111', '11', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (9, 18, '333', '33', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (10, 19, 'wangruixuan', '111', NULL, NULL, NULL, NULL);
INSERT INTO `informations` VALUES (11, 20, 'kobe', '24', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for messages
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages`  (
  `messages_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `timestamp` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`messages_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of messages
-- ----------------------------
INSERT INTO `messages` VALUES (1, 1, '111', '2024-05-05 22:44:33');
INSERT INTO `messages` VALUES (2, 1, 'hhhhhh', '2024-05-05 22:44:43');
INSERT INTO `messages` VALUES (3, 5, '你好', '2024-05-05 22:46:15');
INSERT INTO `messages` VALUES (4, 1, '你好啊！孩子们', '2024-05-05 23:03:44');
INSERT INTO `messages` VALUES (5, 1, '对不起，约基奇，我爱德华兹就是当代乔丹，我就是神！', '2024-05-05 23:07:19');
INSERT INTO `messages` VALUES (6, 5, '我是裘少！', '2024-05-05 23:15:38');
INSERT INTO `messages` VALUES (7, 19, '我是小丑', '2024-05-05 23:30:32');
INSERT INTO `messages` VALUES (8, 3, '加油！克里斯蒂亚诺~罗纳尔多', '2024-05-06 00:06:26');
INSERT INTO `messages` VALUES (9, 1, '七点我要准时开始做购物车模块了', '2024-05-06 19:00:23');
INSERT INTO `messages` VALUES (10, 1, '现在在上一节j2ee的课，老师在讲Servlet', '2024-05-07 14:08:36');
INSERT INTO `messages` VALUES (11, 1, '目前已经完成了登录、注册、充值、购物车购买（包含购买记录）、个人具体信息、道具查看、留言板的前后端业务代码', '2024-05-09 17:52:45');
INSERT INTO `messages` VALUES (12, 5, '裘佳乐实况三连败', '2024-05-10 19:51:09');
INSERT INTO `messages` VALUES (14, 1, '截止今日，该网站已经差不多开发完毕，后台管理系统是本人手搓，不如模版精美，第一次做，乏善可陈！', '2024-05-21 19:29:10');
INSERT INTO `messages` VALUES (15, 1, '儿童节快乐！！', '2024-06-01 16:54:02');
INSERT INTO `messages` VALUES (16, 1, 'jjjjjj', '2024-06-04 15:21:21');
INSERT INTO `messages` VALUES (17, 1, '11', '2024-06-04 15:30:17');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `products_id` int NOT NULL AUTO_INCREMENT,
  `category` int NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `stock` int NULL DEFAULT NULL,
  `product_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`products_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES (1, 1, '加倍道具1', 300.00, '1000万加倍道具套餐1', 5000, 'item1-1.jpg');
INSERT INTO `products` VALUES (2, 1, '加倍道具2', 200.00, '1000万加倍道具套餐2', 5000, 'item1-2.jpg');
INSERT INTO `products` VALUES (3, 2, '功能道具1', 10.00, '对局研究道具', 10000, 'item2-1.jpg');
INSERT INTO `products` VALUES (4, 2, '功能道具2', 50.00, '战绩保护道具', 10000, 'item2-2.png');
INSERT INTO `products` VALUES (5, 1, '加倍道具3', 100.00, '500万加倍道具套餐1', 5000, 'item1-3.png');
INSERT INTO `products` VALUES (6, 1, '加倍道具4', 150.00, '500万加倍道具套餐2', 5000, 'item1-4.jpg');
INSERT INTO `products` VALUES (7, 2, '功能道具3', 20.00, '对弈战绩清零道具', 10000, 'item2-3.jpg');
INSERT INTO `products` VALUES (8, 2, '功能道具4', 50.00, '押分战绩清零道具', 10000, 'item2-4.png');

-- ----------------------------
-- Table structure for properties
-- ----------------------------
DROP TABLE IF EXISTS `properties`;
CREATE TABLE `properties`  (
  `property_id` int NOT NULL AUTO_INCREMENT COMMENT '唯一标识符',
  `user_id` int NULL DEFAULT NULL,
  `balance` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '财产信息',
  PRIMARY KEY (`property_id`) USING BTREE,
  INDEX `id`(`user_id`) USING BTREE,
  CONSTRAINT `properties_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of properties
-- ----------------------------
INSERT INTO `properties` VALUES (1, 1, 730.00);
INSERT INTO `properties` VALUES (2, 2, 700.00);
INSERT INTO `properties` VALUES (3, 3, 30.00);
INSERT INTO `properties` VALUES (4, 4, 0.00);
INSERT INTO `properties` VALUES (5, 5, 180.00);
INSERT INTO `properties` VALUES (6, 6, 300.00);
INSERT INTO `properties` VALUES (7, 9, 200.00);
INSERT INTO `properties` VALUES (13, 18, 150.00);
INSERT INTO `properties` VALUES (14, 19, 0.00);
INSERT INTO `properties` VALUES (15, 20, 50.00);

-- ----------------------------
-- Table structure for purchases
-- ----------------------------
DROP TABLE IF EXISTS `purchases`;
CREATE TABLE `purchases`  (
  `purchases_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `products_description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `purchase_date` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`purchases_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `products_id`(`products_description`) USING BTREE,
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchases
-- ----------------------------
INSERT INTO `purchases` VALUES (1, 1, '1000万加倍道具套餐1', '2024-05-06 21:15:49');
INSERT INTO `purchases` VALUES (2, 1, '1000万加倍道具套餐1', '2024-05-06 21:15:57');
INSERT INTO `purchases` VALUES (3, 1, '1000万加倍道具套餐1', '2024-05-06 21:15:57');
INSERT INTO `purchases` VALUES (4, 1, '1000万加倍道具套餐1', '2024-05-06 21:33:38');
INSERT INTO `purchases` VALUES (5, 1, '500万加倍道具套餐1', '2024-05-06 21:33:38');
INSERT INTO `purchases` VALUES (6, 1, '对局研究道具', '2024-05-06 21:33:38');
INSERT INTO `purchases` VALUES (7, 1, '战绩保护道具', '2024-05-06 21:33:38');
INSERT INTO `purchases` VALUES (8, 5, '对局研究道具', '2024-05-06 21:36:36');
INSERT INTO `purchases` VALUES (9, 5, '500万加倍道具套餐1', '2024-05-06 21:36:36');
INSERT INTO `purchases` VALUES (11, 5, '战绩保护道具', '2024-05-07 14:46:08');
INSERT INTO `purchases` VALUES (12, 5, '500万加倍道具套餐1', '2024-05-07 14:46:08');
INSERT INTO `purchases` VALUES (13, 9, '1000万加倍道具套餐1', '2024-05-07 14:50:36');
INSERT INTO `purchases` VALUES (14, 9, '对局研究道具', '2024-05-07 14:50:36');
INSERT INTO `purchases` VALUES (15, 9, '战绩保护道具', '2024-05-07 14:50:36');
INSERT INTO `purchases` VALUES (19, 5, '500万加倍道具套餐1', '2024-05-07 19:44:39');
INSERT INTO `purchases` VALUES (20, 5, '战绩保护道具', '2024-05-07 19:44:39');
INSERT INTO `purchases` VALUES (21, 5, '500万加倍道具套餐1', '2024-05-07 19:55:36');
INSERT INTO `purchases` VALUES (22, 5, '对局研究道具', '2024-05-07 19:55:36');
INSERT INTO `purchases` VALUES (23, 3, '对局研究道具', '2024-05-07 20:25:58');
INSERT INTO `purchases` VALUES (24, 3, '战绩保护道具', '2024-05-07 20:25:58');
INSERT INTO `purchases` VALUES (25, 3, '对局研究道具', '2024-05-07 20:26:01');
INSERT INTO `purchases` VALUES (26, 3, '对局研究道具', '2024-05-07 20:26:14');
INSERT INTO `purchases` VALUES (27, 3, '战绩保护道具', '2024-05-07 20:26:44');
INSERT INTO `purchases` VALUES (28, 1, '对局研究道具', '2024-05-07 20:28:23');
INSERT INTO `purchases` VALUES (29, 1, '对局研究道具', '2024-05-07 20:28:23');
INSERT INTO `purchases` VALUES (30, 1, '1000万加倍道具套餐1', '2024-05-07 20:28:23');
INSERT INTO `purchases` VALUES (31, 18, '对局研究道具', '2024-05-07 21:05:08');
INSERT INTO `purchases` VALUES (32, 20, '对局研究道具', '2024-05-08 21:10:04');
INSERT INTO `purchases` VALUES (41, 1, '1000万加倍道具套餐1', '2024-05-29 16:45:47');
INSERT INTO `purchases` VALUES (42, 1, '500万加倍道具套餐1', '2024-05-29 16:46:07');
INSERT INTO `purchases` VALUES (43, 1, '对局研究道具', '2024-06-03 10:30:04');
INSERT INTO `purchases` VALUES (44, 1, '1000万加倍道具套餐1', '2024-06-04 15:20:52');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'linzhou', '123456');
INSERT INTO `user` VALUES (2, 'lz', '123456');
INSERT INTO `user` VALUES (3, 'lz666', '123456');
INSERT INTO `user` VALUES (4, 'sb', '654321');
INSERT INTO `user` VALUES (5, 'qiujiale', '123456');
INSERT INTO `user` VALUES (6, 'son', '123');
INSERT INTO `user` VALUES (9, '111', '11');
INSERT INTO `user` VALUES (18, '333', '33');
INSERT INTO `user` VALUES (19, 'wangruixuan', '111');
INSERT INTO `user` VALUES (20, 'kobe', '24');

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `delete_admingrade_trigger`;
delimiter ;;
CREATE TRIGGER `delete_admingrade_trigger` BEFORE DELETE ON `admin` FOR EACH ROW BEGIN
    DELETE FROM admingrade WHERE admin_id = OLD.admin_id;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table admin
-- ----------------------------
DROP TRIGGER IF EXISTS `after_admin_insert`;
delimiter ;;
CREATE TRIGGER `after_admin_insert` AFTER INSERT ON `admin` FOR EACH ROW BEGIN
    -- 向 admingrade 表插入一条关联的记录
    INSERT INTO admingrade (admin_id, grade)
    VALUES (NEW.admin_id, 0);
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table user
-- ----------------------------
DROP TRIGGER IF EXISTS `delete_related_user_trigger`;
delimiter ;;
CREATE TRIGGER `delete_related_user_trigger` BEFORE DELETE ON `user` FOR EACH ROW BEGIN
    -- 删除与该用户相关联的 messages 表中的数据
    DELETE FROM messages WHERE user_id = OLD.id;
    
    -- 删除与该用户相关联的 informations 表中的数据
    DELETE FROM informations WHERE user_id = OLD.id;
		
		DELETE FROM properties WHERE user_id = OLD.id;
		
		DELETE FROM purchases WHERE user_id = OLD.id;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
