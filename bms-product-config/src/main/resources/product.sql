## bms_product
CREATE TABLE `bms_brand` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增品牌ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '授权品牌名称',
  `web` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '品牌官网地址',
  `telephone` VARCHAR(16) NOT NULL DEFAULT '' COMMENT '联系电话',
  `logo` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '品牌logo地址',
  `status` TINYINT NOT NULL DEFAULT '0' COMMENT '品牌状态',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '品牌描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商户被授权品牌';

CREATE TABLE `bms_category` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增一级类别ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '类别名称',
  `second_category_num` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '该类别下的二级类别数量',
  `product_num` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '该类别下的商品数量',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '类别描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商品一级类别';

CREATE TABLE `bms_second_category` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增二级类别ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '二级类别名称',
  `category_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '所属一级类别ID',
  `product_num` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '该类别下的商品数量',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '二级类别描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商品二级类别';

CREATE TABLE `bms_product` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增商品ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `title` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '商品标题',
  `image` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '商品图片地址',
  `brand_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '所属品牌ID',
  `second_category_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '自增二级类别ID',
  `status` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品状态',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '简单描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商户下商品';

CREATE TABLE `bms_product_info` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `product_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品ID',
  `service` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '商品服务',
  `weight` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品重量',
  `color` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品颜色',
  `inventory` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '库存数量',
  `sale` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品销量',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '详细描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商品详细信息';

CREATE TABLE `bms_product_price` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `product_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商品ID',
  `cost_price` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '成本价',
  `market_price` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '市场价',
  `sell_price` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '售价',
  `postage` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '邮费',
  `profits` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '利润',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '价格描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商品价格';