import { Message } from "semantic-ui-react";

function ConditionalBanner(props) {
  if (!props.displayBool) {
    return <div></div>;
  } else {
    if (props.type === "field") {
      return (
        <div>
          <Message negative>
            <Message.Header>Please fill in at least one field.</Message.Header>
          </Message>
        </div>
      );
    } else if (props.type === "error") {
      return (
        <div>
          <Message negative>
            <Message.Header>An error has occurred.</Message.Header>
          </Message>
        </div>
      );
    } else if (props.type === "date") {
      return (
        <div>
          <Message negative>
            <Message.Header>
              Please enter dates in MM/DD/YY format.
            </Message.Header>
          </Message>
        </div>
      );
    } else if (props.type === "success") {
      return (
        <div>
          <Message positive>
            <Message.Header>Success!</Message.Header>
          </Message>
        </div>
      );
    }
  }
}

export default ConditionalBanner;
