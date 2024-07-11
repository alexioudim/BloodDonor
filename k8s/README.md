
# Project set up for Kubernetes (microk8s)

## Postgres Database (PVC, Deployment and Service)

```bash
kubectl apply -f postgres-pvc.yaml
```

```bash
kubectl apply -f postgres-deployment.yaml
```

```bash
kubectl apply -f postgres-svc.yaml
```
## Spring Backend Application (Deployment, Ingress and Service)

```bash
kubectl apply -f spring-deployment.yaml
```

```bash
kubectl apply -f spring-svc.yaml
```

```bash
kubectl apply -f spring-ingress.yaml
```
## VueJs Frontend Application (Deployment, Ingress and Service)

```bash
kubectl apply -f vue-deployment.yaml
```

```bash
kubectl apply -f vue-svc.yaml
```

```bash
kubectl apply -f vue-ingress.yaml
```
