variable "upstash_user" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "upstash_api_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "upstash_db_name" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "aws_access_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "aws_secret_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "telegram_bot_token" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "telegram_webhook_secret" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "backend_url" {
  type      = string
  nullable  = false
  sensitive = true
}
