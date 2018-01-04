export const JOIN_TYPE_CHANGE = "join_type_change";

export const changeJoinType = (type) => {
    return {
        type: JOIN_TYPE_CHANGE,
        payload: type
    }
};