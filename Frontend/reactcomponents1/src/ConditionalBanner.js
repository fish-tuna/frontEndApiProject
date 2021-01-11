import { Message } from "semantic-ui-react";

function ConditionalBanner(props) {
  if (props.displayBool) {
    return (
      <div>
        <Message negative>
          <Message.Header>
            Please fill in at least one search field.
          </Message.Header>
        </Message>
      </div>
    );
  } else {
    return <div></div>;
  }
}

export default ConditionalBanner;
