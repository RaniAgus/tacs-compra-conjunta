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

variable "database_admin_password" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "database_app_password" {
  type      = string
  nullable  = false
  sensitive = true
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

variable "jwt_secret_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "cloudflare_account_id" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "cloudflare_access_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "cloudflare_secret_key" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "cloudflare_public_endpoint" {
  type      = string
  nullable  = false
}

variable "vercel_api_token" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "vercel_org_id" {
  type      = string
  nullable  = false
  sensitive = true
}

variable "vercel_project_name" {
  type    = string
  default = "compra-conjunta-backend"
  sensitive = true
}

variable "github_repo" {
  type      = string
  default   = "fpesce27/TP_TACS_2024C1_GRUPO_1"
  sensitive = true
}
