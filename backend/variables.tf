variable "database_uri" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "do_app_name" {
  type     = string
  nullable = false
}

variable "do_token" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "do_container_registry" {
  type      = string
  nullable  = false
}

variable "do_container_repository" {
  type     = string
  nullable = false
}

variable "do_container_tag" {
  type     = string
  nullable = false
}

variable "jwt_secret_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "s3_endpoint" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "s3_access_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "s3_secret_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "s3_bucket_name" {
  type      = string
  nullable  = false
}

variable "s3_region" {
  type      = string
  nullable  = false
}

variable "s3_public_endpoint" {
  type      = string
  nullable  = false
}
