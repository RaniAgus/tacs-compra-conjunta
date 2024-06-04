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

provider "vercel" {
  api_token = var.vercel_api_token
  team      = var.vercel_org_id
}

resource "vercel_project" "frontend" {
  name           = var.vercel_project_name
  framework      = "nextjs"
  root_directory = "frontend"
  git_repository = {
    type              = "github"
    repo              = var.vercel_project_repo
    production_branch = var.vercel_project_branch
  }
  environment    = [ {
    key    = "BACKEND_URL"
    value  = digitalocean_app.app.live_url
    target = ["production"]
  } ]
}


resource "vercel_deployment" "frontend" {
  project_id = vercel_project.frontend.id
  ref        = var.vercel_project_branch
}
