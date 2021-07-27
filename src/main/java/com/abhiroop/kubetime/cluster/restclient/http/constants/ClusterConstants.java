package com.abhiroop.kubetime.cluster.restclient.http.constants;

public interface ClusterConstants {

	interface RestApiRelativePath {

		public static final String CLUSTER_VERSIONS = "apis/config.openshift.io/v1/clusterversions/version";
	}

	interface ResourceStatus {

		public static final String ACTIVE = "Active";
		public static final String TERMINATING = "Terminating";
	}

	enum MemoryUnit {

		Ki, Mi, Gi, Ti, Pi, Ei;

	}
}
