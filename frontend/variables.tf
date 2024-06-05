variable "backend_url" {
  type      = string
  nullable  = false
  sensitive = true
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

variable "vercel_project_repo" {
  type     = string
  nullable = false
}

variable "vercel_project_branch" {
  type     = string
  nullable = false
  default  = "main"
}

variable "vercel_project_name" {
  type     = string
  nullable = false
}
