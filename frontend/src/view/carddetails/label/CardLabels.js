import {Button, IconButton} from "@mui/material";
import * as React from "react";
import {useSelector} from "react-redux";
import AddLabel from "./AddLabel";
import {Add} from "@mui/icons-material";

const CardLabels = () => {
    const cardLabels = useSelector(sts => sts.dashboard.cardLabels)

    return (
        <div>
            <h3 style={{
                padding: 0,
                margin: 0,
                fontSize: 12,
                fontWeight: 600
            }}>
                Labels
            </h3>
            <div>
                {cardLabels.map((data) => {
                    return (
                        <Button
                            disableElevation
                            size='small'
                            variant='contained'
                            color={data.color}
                            style={{borderRadius: 3, margin: 2, textTransform: 'none'}}
                            key={data.id}
                        >
                            {data.title}
                        </Button>
                    );
                })}

                <AddLabel
                    activator={e => (
                        <IconButton
                            size='small'
                            variant='contained'
                            style={{
                                borderRadius: 3,
                                margin: 2,
                                backgroundColor: 'rgba(9, 30, 66, 0.04)'
                            }}
                            {...e}
                        >
                            <Add/>
                        </IconButton>
                    )}
                />
            </div>
        </div>
    );
};

export default CardLabels