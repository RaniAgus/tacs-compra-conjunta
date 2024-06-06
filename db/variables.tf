variable "mongodbatlas_public_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "mongodbatlas_private_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "mongodbatlas_project_name" {
  type    = string
  nullable  = false
}

variable "mongodbatlas_allowed_cidr_block" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "mongodbatlas_cluster_name" {
  type     = string
  nullable = false
}

variable "mongodb_auth_database_name" {
  type      = string
  default   = "admin"
}

variable "mongodb_admin_username" {
  type    = string
  default = "admin"
}

variable "mongodb_admin_password" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "mongodb_app_username" {
  type    = string
  default = "appuser"
}

variable "mongodb_app_password" {
  type      = string
  nullable  = false
  sensitive = true
}
