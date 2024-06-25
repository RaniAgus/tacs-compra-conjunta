terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "5.55.0"
    }
    upstash = {
      source = "upstash/upstash"
      version = "1.5.3"
    }
  }
}

provider "upstash" {
  email   = var.upstash_user
  api_key = var.upstash_api_key
}

resource "upstash_redis_database" "telegram" {
  database_name = var.upstash_db_name
  region        = "us-east-1"
  tls           = true
  eviction      = true
  auto_scale    = false
}

provider "aws" {
  region     = "us-east-1"
  access_key = var.aws_access_key
  secret_key = var.aws_secret_key
}

resource "aws_ssm_parameter" "telegram_bot_token" {
  name  = "/telegram/bot/token"
  type  = "SecureString"
  value = var.telegram_bot_token
}

resource "aws_ssm_parameter" "telegram_webhook_secret" {
  name  = "/telegram/webhook/secret"
  type  = "SecureString"
  value = var.telegram_webhook_secret
}

resource "aws_ssm_parameter" "redis_endpoint" {
  name  = "/redis/endpoint"
  type  = "SecureString"
  value = "rediss://default:${upstash_redis_database.telegram.password}@${upstash_redis_database.telegram.endpoint}:${upstash_redis_database.telegram.port}"
}

resource "aws_ssm_parameter" "backend_url" {
  name  = "/backend/url"
  type  = "SecureString"
  value = var.backend_url
}
