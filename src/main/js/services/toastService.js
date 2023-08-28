import {toast} from "react-toastify";

export function toastErrors(errors) {
    errors.forEach(error => toast.error(error));
}