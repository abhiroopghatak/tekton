import {combineReducers} from "redux";
import {reducer as formReducer} from "redux-form";

import {
    homePageDataReducer,
    googleAuthReducer,
	searchKeywordReducer
} from "./screens/commonScreenReducer";



export default combineReducers({
    form: 
    googleAuthReducer,
    searchKeywordReducer,
    homePageDataReducer
});

