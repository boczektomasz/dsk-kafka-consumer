Add `git-clone` task:

```
oc apply -f https://raw.githubusercontent.com/tektoncd/catalog/main/task/git-clone/0.5/git-clone.yaml
```

Add `buildah` task:

```
oc apply -f https://raw.githubusercontent.com/tektoncd/catalog/main/task/buildah/0.3/buildah.yaml
```

Add privileges to pipeline service account in confluent namespace:

```
oc adm policy add-scc-to-user privileged -z pipeline -n confluent
```
