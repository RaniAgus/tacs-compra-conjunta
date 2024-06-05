module "db" {
  source = "./db"

  mongodbatlas_project_name       = "tacs"
  mongodbatlas_cluster_name       = "compraconjunta"
  mongodbatlas_public_key         = var.mongodbatlas_public_key
  mongodbatlas_private_key        = var.mongodbatlas_private_key
  mongodbatlas_allowed_cidr_block = "0.0.0.0/0"

  mongodb_admin_password = var.database_admin_password
  mongodb_app_password   = var.database_app_password
}

module "backend" {
  source = "./backend"

  do_app_name = "tacs-compra-conjunta"
  do_token    = var.do_token
  do_registry = var.do_registry

  database_uri   = "mongodb+srv://${module.db.app_username}:${var.database_app_password}@${module.db.domain}/compraconjunta?retryWrites=true&w=majority&appName=compraConjuntaBackend"
  jwt_secret_key = var.jwt_secret_key

  s3_endpoint        = "https://${var.cloudflare_account_id}.r2.cloudflarestorage.com"
  s3_access_key      = var.cloudflare_access_key
  s3_secret_key      = var.cloudflare_secret_key
  s3_bucket_name     = "compra-conjunta"
  s3_public_endpoint = var.cloudflare_public_endpoint
}

module "frontend" {
  source = "./frontend"

  vercel_org_id         = var.vercel_org_id
  vercel_api_token      = var.vercel_api_token
  vercel_project_name   = var.vercel_project_name
  vercel_project_repo   = var.github_repo
  vercel_project_branch = var.github_production_branch

  backend_url = module.backend.backend_url
}