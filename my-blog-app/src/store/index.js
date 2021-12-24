import {createStore} from "vuex"

const store = createStore({
    state: {
        userinfo: {
            id: '',
            username: '',
            token: '',
            avatar: '',
        }
    },
    mutations: {
        setUserInfo(state, user) {
            state.userinfo.id = user.id
            state.userinfo.username = user.username
            state.userinfo.token = user.token
            if(user.avatar)
                state.userinfo.avatar = user.avatar
        },
        delUserInfo(state) {
            state.userinfo = {
                id: '',
                username: '',
                token: '',
                avatar: '',
            }
        }
    },
    getters: {
        getUserInfo(state) {
            return JSON.stringify(state.userinfo)
        },
        getToken(state){
            return state.userinfo.token
        }
    },
    actions: {},
    modules: {}
})
export default store