CREATE DATABASE IF NOT EXISTS benefitmoa;

CREATE USER IF NOT EXISTS 'benefitmoa_user'@'%' IDENTIFIED BY 'benefitpass'; ## 로컬 테스트용

GRANT ALL PRIVILEGES ON benefitmoa.* TO 'benefitmoa_user'@'%';

FLUSH PRIVILEGES;


-- 생성한 데이터베이스 사용
USE benefitmoa;

-- policy 테이블 없으면 생성
CREATE TABLE IF NOT EXISTS `policy` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `user_type` VARCHAR(255) NOT NULL,
    `detail_url` VARCHAR(1000),
    `service_summary` VARCHAR(1000) NOT NULL,
    `category` VARCHAR(255) NOT NULL,
    `selection_criteria` TEXT,
    `support_content` TEXT,
    `target_audience` TEXT,
    `support_type` VARCHAR(255),
    `application_period` VARCHAR(500),
    `application_method` VARCHAR(1000),
    `contact` VARCHAR(255),
    `reception_agency` VARCHAR(255),
    `service_id` VARCHAR(255),
    `department_name` VARCHAR(255),
    `organization_name` VARCHAR(255),
    `organization_type` VARCHAR(255),
    `organization_code` VARCHAR(255),
    `policy_created_at` VARCHAR(255),
    `policy_updated_at` VARCHAR(255),
    `view_count` INT DEFAULT 0,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX `idx_category` (`category`),
    INDEX `idx_user_type` (`user_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='정책 정보 테이블';
