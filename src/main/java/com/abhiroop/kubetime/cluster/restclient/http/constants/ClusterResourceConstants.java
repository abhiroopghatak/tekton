package com.abhiroop.kubetime.cluster.restclient.http.constants;

public interface ClusterResourceConstants {

	 // OpenShift Kinds
    public static final String BUILD = "Build";
    public static final String BUILD_CONFIG = "BuildConfig";
    public static final String DEPLOYMENT_CONFIG = "DeploymentConfig";
    public static final String IMAGE_STREAM = "ImageStream";
    public static final String IMAGE_STREAM_TAG = "ImageStreamTag";
    public static final String IMAGE_STREAM_IMPORT = "ImageStreamImport";
    public static final String NAMESPACE = "Namespace";
    public static final String OAUTH_ACCESS_TOKEN = "OAuthAccessToken";
    public static final String OAUTH_AUTHORIZE_TOKEN = "OAuthAuthorizeToken";
    public static final String OAUTH_CLIENT = "OAuthClient";
    public static final String OAUTH_CLIENT_AUTHORIZATION = "OAuthClientAuthorization";
    public static final String POLICY = "Policy";
    public static final String POLICY_BINDING = "PolicyBinding";
    public static final String PROJECT = "Project";
    public static final String PROJECT_REQUEST = "ProjectRequest";
    public static final String ROLE = "Role";
    public static final String ROLE_BINDING = "RoleBinding";
    public static final String ROUTE = "Route";
    public static final String TEMPLATE = "Template";
    public static final String USER = "User";
    public static final String GROUP = "Group";
    public static final String IDENTITY = "Identity";

    // Kubernetes Kinds
    public static final String ENDPOINTS = "Endpoints";
    public static final String EVENT = "Event";
    public static final String LIMIT_RANGE = "LimitRange";
    public static final String POD = "Pod";
    public static final String PVC = "PersistentVolumeClaim";
    public static final String PERSISTENT_VOLUME = "PersistentVolume";
    public static final String REPLICATION_CONTROLLER = "ReplicationController";
    public static final String RESOURCE_QUOTA = "ResourceQuota";
    public static final String SERVICE = "Service";
    public static final String SECRET = "Secret";
    public static final String SERVICE_ACCOUNT = "ServiceAccount";
    public static final String CONFIG_MAP = "ConfigMap";
    /*
     * These are not true resources that can be used (mostly) in RESTful operations
     */
    public static final String BUILD_REQUEST = "BuildRequest";

    @Deprecated
    public static final String CONFIG = "Config";// not rest resource;
    public static final String LIST = "List";
    public static final String STATUS = "Status";// not rest resource
    public static final String PROCESSED_TEMPLATES = "ProcessedTemplates";// mechanism for processing templates

    /**
     * The default if we haven't implemented the kind yet
     */
    public static final String UNRECOGNIZED = "Unrecognized";

    
}
