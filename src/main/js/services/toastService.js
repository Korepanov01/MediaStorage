import {toast} from "react-toastify";

export function toastErrors(messages) {
    messages.forEach(message => toast.error(message));
}