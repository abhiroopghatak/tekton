import {
	LOAD_DASHBOARD
	} from "../../action/types";
	
import log from "loglevel";

export const googleAuthReducer = (state
                                      = {isSignedInUsingOAuth: null, firstName: null, oAuth: null}, action) => {
   
            return state;
    
};
export const searchKeywordReducer = (state
                                           = {error: false, errorMsg: null, data: [{keyword: ' '}]}, action) => {
    switch (action.type) {
        case SEARCH_KEYWORD:
            return {...action.payload, error: false, errorMsg: null}
        case SEARCH_KEYWORD_ERROR:
            return {...state, error: true, errorMsg: "Something went wrong"};
        default:
            return state;
    }
};
export const homePageDataReducer = (state = {isLoading: true}, action) => {
    log.trace(`[HOME_SCREEN_REDUCER]: action.type = ${action.type}`)
    switch (action.type) {
        case LOAD_HOME_PAGE:
            log.trace(`[HOME_SCREEN_REDUCER]: action.payload = ${JSON.stringify(action.payload)}`)
            return action.payload;
        default:
            return state;
    }
};