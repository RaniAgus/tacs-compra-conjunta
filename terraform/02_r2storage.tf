# Como la API de Cloudflare R2 es muy limitada, tenemos que crear el bucket
# manualmente y configurar estas variables en el archivo terraform.tfvars
# para que la aplicación pueda acceder al bucket.

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

variable "cloudflare_bucket_name" {
  type      = string
  nullable  = false
}

variable "cloudflare_public_endpoint" {
  type      = string
  nullable  = false
}
