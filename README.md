# Chess Game

Developer: Szekely Adam

This project was made in the first half of 2024, for the Software Development subject at the University of Debrecen.

## The task

"Our task is to move from the first chess position shown in the illustrations to the second chess position.

The pieces can be moved according to the rules of chess."

<table style="border-collapse: collapse; width: 100%;">
    <tbody>
        <tr>
            <td style="color: black; background-color: gray; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9812</td>
            <td style="color: black; background-color: white; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9815</td>
            <td style="color: black; background-color: gray; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9815</td>
        </tr>
        <tr>
            <td style="color: black; background-color: white; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9814</td>
            <td style="color: black; background-color: gray; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9814</td>
            <td style="color: black; background-color: white; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;"></td>
        </tr>
    </tbody>
</table>

<table style="border-collapse: collapse; width: 100%;">
    <tbody>
        <tr>
            <td style="color: black; background-color: gray; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9815</td>
            <td style="color: black; background-color: white; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9815</td>
            <td style="color: black; background-color: gray; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;"></td>
        </tr>
        <tr>
            <td style="color: black; background-color: white; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9814</td>
            <td style="color: black; background-color: gray; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9814</td>
            <td style="color: black; background-color: white; border: 1px solid black; width: 50px; height: 50px; text-align: center; font-size: 32px;">&#9812</td>
        </tr>
    </tbody>
</table>

## Solution

1. (0,1)->(1,2)
1. (0,0)->(0,1)
1. (1,0)->(0,0)
1. (1,1)->(1,0)
1. (0,2)->(1,1)
1. (0,1)->(0,2)
1. (0,0)->(0,1)
1. (1,1)->(0,0)
1. (0,1)->(1,1)
1. (1,2)->(0,1)
1. (0,2)->(1,2)