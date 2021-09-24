package com.abhiroop.kubetime.cluster.restclient.http.constants.openshift4;

public interface ClusterConstants {

	interface RestApiRelativePath {

		public static final String CLUSTER_SERVICE = "/api/v1/namespaces/kube-system/services?labelSelector=kubernetes.io=cluster-service";
		public static final String CLUSTER_VERSIONS = "/apis/config.openshift.io/v1/clusterversions/version";
		public static final String CLUSTER_METADATA = "/version";
		public static final String CLUSTER_NAMESPACE_LIST = "/api/v1/namespaces";
		public static final String CLUSTER_NAMESPACE_POD_RESORCE_PREFIX = "/apis/metrics.k8s.io/v1beta1/namespaces";
		
	}

	interface ResourceStatus {

		public static final String ACTIVE = "Active";
		public static final String TERMINATING = "Terminating";
	}

	enum MemoryUnit {

		Ki, Mi, Gi, Ti, Pi, Ei;

	}
}
