export const FUZZY_LIKE_MASK_CHANGE = "fuzzy_like_value_change";

export const onFuzzyLikeMaskChange = (value) => {
    return {
        type: FUZZY_LIKE_MASK_CHANGE,
        payload: value
    };
};