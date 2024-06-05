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

variable "do_registry" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "do_repository" {
  type    = string
  default = "tacs_compra_conjunta"
}

variable "do_backend_tag" {
  type      = string
  default = "backend"
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
  nullable  = true
}

variable "s3_public_endpoint" {
  type      = string
  nullable  = false
}
